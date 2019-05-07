private void validateUpperBranch(int pos, int axis, float median) {
    float value = median(pos, axis);
    if (value < median)
        System.out.println("error!");
    int child1 = 2 * pos + 1, child2 = 2 * pos + 2;
    if (child1 < photon.length)
        validateUpperBranch(child1, axis, median);
    if (child2 < photon.length)
        validateUpperBranch(child2, axis, median);
}
