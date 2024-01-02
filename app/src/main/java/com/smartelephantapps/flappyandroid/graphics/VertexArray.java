package com.smartelephantapps.flappyandroid.graphics;

import static android.opengl.GLES30.*;

import com.smartelephantapps.flappyandroid.utils.BufferUtils;

public class VertexArray {

    private int[] vao = new int[1];
    private int[] vbo = new int[1];
    private int[] ibo = new int[1];
    private int[] tbo = new int[1];
    private int count;

    public VertexArray(int count) {
        this.count = count;

        glGenVertexArrays(1, vao, 0);
    }

    public VertexArray(float[] vertices, byte[] indices, float[] textureCoordinates) {
        count = indices.length;

        glGenVertexArrays(1, vao, 0);
        glBindVertexArray(vao[0]);

        glGenBuffers(1, vbo, 0);
        glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
        glBufferData(GL_ARRAY_BUFFER, vertices.length * 4, BufferUtils.createFloatBuffer(vertices), GL_STATIC_DRAW);
        glVertexAttribPointer(Shader.VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(Shader.VERTEX_ATTRIB);

        glGenBuffers(1, tbo, 0);
        glBindBuffer(GL_ARRAY_BUFFER, tbo[0]);
        glBufferData(GL_ARRAY_BUFFER, textureCoordinates.length * 4, BufferUtils.createFloatBuffer(textureCoordinates), GL_STATIC_DRAW);
        glVertexAttribPointer(Shader.TCOORD_ATTRIB, 2, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(Shader.TCOORD_ATTRIB);

        glGenBuffers(1, ibo, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo[0]);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices.length * 1, BufferUtils.createByteBuffer(indices), GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public void bind() {
        glBindVertexArray(vao[0]);
        if (ibo[0] > 0)
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo[0]);
    }

    public void unbind() {
        if (ibo[0] > 0)
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

        glBindVertexArray(0);
    }

    public void draw() {
        if (ibo[0] > 0)
            glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_BYTE, 0);
        else
            glDrawArrays(GL_TRIANGLES, 0, count);
    }

    public void render() {
        bind();
        draw();
    }
}
