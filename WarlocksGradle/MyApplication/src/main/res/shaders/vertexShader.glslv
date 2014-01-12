#version 330

//Matrices
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 modelMatrix;
//In values
layout(location = 0) in vec3 position;
layout(location = 1) in vec4 color;
//Out values
out vec4 frag_color;
//Main shader
void main(void)
{
    //Position in world
    gl_Position = vec4(position, 1.0);
    //gl_Position = projectionMatrix * viewMatrix * modelMatrix * vec4(in_Position, 1.0);
    //No color changes
    frag_color = color;
}