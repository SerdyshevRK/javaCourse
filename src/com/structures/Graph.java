package com.structures;

import java.util.*;
import java.util.function.Predicate;

public class Graph<T> {
    private int maxSize = 50;
    private int actualSize;
    private double[][] graphMatrix;
    private Map<Integer, T> vertices;

    public Graph() {
        graphMatrix = new double[maxSize][maxSize];
        initialMatrixFill(graphMatrix);
        vertices = new HashMap<>();
        actualSize = vertices.size();
    }

    private void initialMatrixFill(double[][] matrix) {
        for (double[] array : matrix) {
            Arrays.fill(array, Double.NaN);
        }
    }

    public void addVertex(int index, T object) {
        vertices.put(index, object);
        actualSize = vertices.size();
        resizeMatrix();
    }

    public void removeVertex(int index) {
        if (!vertices.containsKey(index))
            return;
        vertices.remove(index);
        actualSize = vertices.size();
    }

    public void addEdge(int firstVertex, int secondVertex, double weight) {
        graphMatrix[firstVertex][secondVertex] = weight;
        graphMatrix[secondVertex][firstVertex] = weight;
    }

    public void addEdge(int firstVertex, int secondVertex) {
        addEdge(firstVertex, secondVertex, 1);
    }

    public void addDirectionalEdge(int startVertex, int endVertex, double weight) {
        graphMatrix[startVertex][endVertex] = weight;
    }

    public void addDirectionalEdge(int startVertex, int endVertex) {
        addDirectionalEdge(startVertex, endVertex, 1);
    }

    public void removeEdge(int firstVertex, int secondVertex) {
        addEdge(firstVertex, secondVertex, Double.NaN);
    }

    private void resizeMatrix() {
        if (actualSize < maxSize)
            return;

        int newSize = maxSize * 2;
        double[][] matrix = new double[newSize][newSize];
        initialMatrixFill(matrix);

        for (int i = 0; i < maxSize; i++) {
            System.arraycopy(graphMatrix[i], 0, matrix[i], 0, maxSize);
        }

        maxSize = newSize;
    }

    public int size() {
        return actualSize;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, T> entry : vertices.entrySet()) {
            sb.append(entry.getValue().toString()).append("{ ");
            for (int i = 0; i <= actualSize; i++) {
                if (graphMatrix[entry.getKey()][i] < 0)
                    continue;
                sb.append(vertices.get(i).toString()).append(" ");
            }
            sb.append("}\n");
        }
        return sb.toString();
    }

    public T getVertex(int index) {
        return vertices.get(index);
    }

    public List<T> getNeighbours(int index) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i <= actualSize; i++) {
            if (Double.isNaN(graphMatrix[index][i]))
                continue;

            list.add(vertices.get(i));
        }
        return list;
    }

    public int search_BFS(Predicate<T> predicate) {
        Queue<Integer> verticesToOpen = new ArrayDeque<>();
        List<Integer> visited = new ArrayList<>();
        int index;

        verticesToOpen.add(0);
        while (!verticesToOpen.isEmpty()) {
            index = verticesToOpen.poll();
            visited.add(index);
            if (predicate.test(vertices.get(index))) {
                return index;
            }
            for (int i = 0; i < actualSize; i++) {
                if (Double.isNaN(graphMatrix[index][i]) || visited.contains(i) || verticesToOpen.contains(i))
                    continue;

                verticesToOpen.add(i);
            }
        }

        return -1;
    }

    public int search_DFS(Predicate<T> predicate) {
        List<Integer> visited = new ArrayList<>();
        return search_DFS(0, visited, predicate);
    }

    private int search_DFS(int index, List<Integer> visited, Predicate<T> predicate) {
        visited.add(index);
        if (predicate.test(vertices.get(index))) {
            return index;
        }
        int idx = -1;
        for (int i = 0; i < actualSize; i++) {
            if (Double.isNaN(graphMatrix[index][i]) || visited.contains(i))
                continue;
            idx = search_DFS(i, visited, predicate);
            if (idx > 0) break;
        }
        return idx;
    }
}
