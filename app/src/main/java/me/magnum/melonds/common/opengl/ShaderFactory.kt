package me.magnum.melonds.common.opengl

import android.opengl.GLES20

object ShaderFactory {
    fun createShaderProgram(source: ShaderProgramSource): Shader {
        val shaderProgram = createShaderProgram(source.vertexShaderSource, source.fragmentShaderSource)
        val textureFilter = when (source.textureFiltering) {
            ShaderProgramSource.TextureFiltering.NEAREST -> GLES20.GL_NEAREST
            ShaderProgramSource.TextureFiltering.LINEAR -> GLES20.GL_LINEAR
        }

        return Shader(shaderProgram, textureFilter)
    }

    private fun createShaderProgram(vertexShader: String, fragmentShader: String): Int {
        val program = GLES20.glCreateProgram()
        GLES20.glAttachShader(program, createShader(GLES20.GL_VERTEX_SHADER, vertexShader))
        GLES20.glAttachShader(program, createShader(GLES20.GL_FRAGMENT_SHADER, fragmentShader))
        GLES20.glLinkProgram(program)
        val result = IntArray(1)
        GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, result, 0)

        if (result[0] == GLES20.GL_FALSE) {
            System.err.println(GLES20.glGetProgramInfoLog(program))
        }

        return program
    }

    private fun createShader(shaderType: Int, code: String): Int {
        val shader = GLES20.glCreateShader(shaderType)
        GLES20.glShaderSource(shader, code)
        GLES20.glCompileShader(shader)
        val result = IntArray(1)
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, result, 0)

        if (result[0] == GLES20.GL_FALSE) {
            System.err.println(GLES20.glGetShaderInfoLog(shader))
        }

        return shader
    }
}