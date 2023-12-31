public void scatterPhoton(ShadingState state, Color power) {
    // make sure we are on the right side of the material  
    state.faceforward();
    Color d = getDiffuse(state);
    state.storePhoton(state.getRay().getDirection(), power, d);
    float avgD = d.getAverage();
    float avgS = spec.getAverage();
    double rnd = state.getRandom(0, 0, 1);
    if (rnd < avgD) {
        // photon is scattered diffusely  
        power.mul(d).mul(1.0f / avgD);
        OrthoNormalBasis onb = state.getBasis();
        double u = 2 * Math.PI * rnd / avgD;
        double v = state.getRandom(0, 1, 1);
        float s = (float) Math.sqrt(v);
        float s1 = (float) Math.sqrt(1.0f - v);
        Vector3 w = new Vector3((float) Math.cos(u) * s, (float) Math.sin(u) * s, s1);
        w = onb.transform(w, new Vector3());
        state.traceDiffusePhoton(new Ray(state.getPoint(), w), power);
    } else if (rnd < avgD + avgS) {
        // photon is scattered specularly  
        float dn = 2.0f * state.getCosND();
        // reflected direction  
        Vector3 refDir = new Vector3();
        refDir.x = (dn * state.getNormal().x) + state.getRay().dx;
        refDir.y = (dn * state.getNormal().y) + state.getRay().dy;
        refDir.z = (dn * state.getNormal().z) + state.getRay().dz;
        power.mul(spec).mul(1.0f / avgS);
        OrthoNormalBasis onb = state.getBasis();
        double u = 2 * Math.PI * (rnd - avgD) / avgS;
        double v = state.getRandom(0, 1, 1);
        float s = (float) Math.pow(v, 1 / (this.power + 1));
        float s1 = (float) Math.sqrt(1 - s * s);
        Vector3 w = new Vector3((float) Math.cos(u) * s1, (float) Math.sin(u) * s1, s);
        w = onb.transform(w, new Vector3());
        state.traceReflectionPhoton(new Ray(state.getPoint(), w), power);
    }
}
