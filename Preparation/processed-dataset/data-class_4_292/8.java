static boolean equals(Object o1, Object o2) {
    if (o1 == o2) {
        return true;
    }
    return (o1 == null) ? o2 == null : o1.equals(o2);
}
