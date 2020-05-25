package ru.kdv.union_find;

public interface UnionFind {
    int count();
    int find(int p);
    boolean connected(int p, int q);
    void union(int p, int q);
}
