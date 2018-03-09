package com.structures;

import java.util.*;

public class Graph {
    private int maxSize = 50;
    private int actualSize;
    private int[][] graphMatrix;
    private Map<Integer, Object> vertices;

    public Graph() {
        graphMatrix = new int[maxSize][maxSize];
        initialMatrixFill(graphMatrix);
        vertices = new HashMap<>();
        actualSize = vertices.size();
    }

    private void initialMatrixFill(int[][] matrix) {
        for (int[] array : matrix) {
            Arrays.fill(array, -1);
        }
    }

    public void addVertex(int index, Object o) {
        vertices.put(index, o);
        actualSize = vertices.size();
        resizeMatrix();
    }

    public void removeVertex(int index) {
        if (!vertices.containsKey(index))
            return;
        vertices.remove(index);
        actualSize = vertices.size();
    }

    public void addEdge(int firstVertex, int secondVertex, int weight) {
        graphMatrix[firstVertex][secondVertex] = weight;
        graphMatrix[secondVertex][firstVertex] = weight;
    }

    public void addEdge(int firstVertex, int secondVertex) {
        addEdge(firstVertex, secondVertex, 1);
    }

    public void addDirectionalEdge(int startVertex, int endVertex, int weight) {
        graphMatrix[startVertex][endVertex] = weight;
    }

    public void addDirectionalEdge(int startVertex, int endVertex) {
        addDirectionalEdge(startVertex, endVertex, 1);
    }

    public void removeEdge(int firstVertex, int secondVertex) {
        addEdge(firstVertex, secondVertex, -1);
    }

    private void resizeMatrix() {
        if (actualSize < maxSize)
            return;

        int newSize = maxSize * 2;
        int[][] matrix = new int[newSize][newSize];
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
        for (Map.Entry<Integer, Object> entry : vertices.entrySet()) {
            sb.append(entry.getValue().toString()).append("{");
            for (int i = 0; i <= actualSize; i++) {
                if (graphMatrix[entry.getKey()][i] < 0)
                    continue;
                sb.append(vertices.get(i).toString()).append(" ");
            }
            sb.append("}\n");
        }
        return sb.toString();
    }

    public Object getVertex(int index) {
        return vertices.get(index);
    }

    public List<Object> getNeighbours(int index) {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i <= actualSize; i++) {
            if (graphMatrix[index][i] < 0)
                continue;

            list.add(vertices.get(i));
        }
        return list;
    }
}
