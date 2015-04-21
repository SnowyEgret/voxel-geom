package ds.geom.test;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_POINTS;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glPointSize;
import static org.lwjgl.opengl.GL11.glVertex2i;
import static org.lwjgl.opengl.GL11.glVertex3d;

import javax.vecmath.Point3d;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import ds.geom.IDrawable;

public class Viewer {

	public Viewer(IDrawable drawable) {
		int w = 300;
		try {
			Display.setDisplayMode(new DisplayMode(w, w));
			Display.setTitle(drawable.getClass().getSimpleName());
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(-w, w, -w, w, 1, -1);
		glMatrixMode(GL_MODELVIEW);

		while (!Display.isCloseRequested()) {
			Display.update();
			Display.sync(60);

			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glColor3f(0.2f, 0.2f, 0.2f);

			glBegin(GL_LINES);
			glVertex2i(-w, 0);
			glVertex2i(w, 0);
			glEnd();

			glBegin(GL_LINES);
			glVertex2i(0, w);
			glVertex2i(0, -w);
			glEnd();

			glColor3f(1f, 1f, 1f);

			glBegin(GL_POINTS);
			glPointSize(1);
			for (Point3d p : drawable.pointSet()) {
				glVertex3d(p.x, p.y, p.z);
			}
			glEnd();

		}

		Display.destroy();
	}

	// TODO Broken. Create test class.
	private String getTestMethodName() {
		for (StackTraceElement e : Thread.currentThread().getStackTrace()) {
			String name = e.getClassName();
			if (name.startsWith("T_") || name.startsWith("PT_"))
				return name + "." + e.getMethodName();
		}
		return null;
	}
}
