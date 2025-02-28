#version 410

uniform samplerCube u_cubemap;
uniform mat4 u_cameraMatrix;

in vec4 v_view;	// WORLD

layout(location = 0) out vec4 o_colour;

const vec3 GAMMA = vec3(2.2);
const float INFINITY = 1. / 0.;

vec3 skyColour(vec4 v) {
	// TODO: Change this to use the cubemap
	return vec3(0.5,0.5,1);
}

float hitPlane(vec4 p, vec4 v) {
	// TODO: Change this to implement equation from workshop
	return INFINITY;
}

vec3 planeColour(vec4 p, vec4 v) {
	// TODO: Change this to implement ambient & diffuse lighting
	return vec3(0,1,0);
}

void main() {
	vec4 p = u_cameraMatrix[3];	// camera position
	vec4 v = normalize(v_view);	// view vector

	// if we don't hit anything, use the sky colour
	float dist = INFINITY;
	vec3 colour = skyColour(v);	

	// if we hit the plane, use the plane colour
	float t = hitPlane(p,v);	
	if (t >= 0 && t < dist) {
		dist = t;
		vec4 hit = p + v * t;
		colour = planeColour(hit, v);
	}
		
	colour = pow(colour,1./GAMMA);	// gamma correction
	o_colour = vec4(colour, 1);			
}


