package graph;

import java.util.*;

public class GraphAdjacencyMatrix<V,E> implements Graph<V,E> {

    private Map< Vertex<V>, Map<Vertex<V>, Edge<E,V>>> adjacencyMap;

    public GraphAdjacencyMatrix() {
        this.adjacencyMap = new HashMap<>();
    }

    @Override
    public boolean areAdjacent(Vertex<V> u, Vertex<V> v) throws InvalidVertexException {
        checkVertex(u);
        checkVertex(v);

        return adjacencyMap.get(u).containsKey(v);
    }

    @Override
    public int numVertices() {
        return 0;
    }

    @Override
    public int numEdges() {
        return 0;
    }

    @Override
    public Collection<Vertex<V>> vertices() {
        return null;
    }

    @Override
    public Collection<Edge<E, V>> edges() {
        return null;
    }

    @Override
    public Collection<Edge<E, V>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
        return null;
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E, V> e) throws InvalidVertexException, InvalidEdgeException {
        return null;
    }

    @Override
    public Vertex<V> insertVertex(V vElement) throws InvalidVertexException {
        return null;
    }

    @Override
    public Edge<E, V> insertEdge(Vertex<V> u, Vertex<V> v, E edgeElement) throws InvalidVertexException, InvalidEdgeException {
        return null;
    }

    @Override
    public Edge<E, V> insertEdge(V vElement1, V vElement2, E edgeElement) throws InvalidVertexException, InvalidEdgeException {
        return null;
    }

    @Override
    public V removeVertex(Vertex<V> v) throws InvalidVertexException {
        return null;
    }

    @Override
    public E removeEdge(Edge<E, V> e) throws InvalidEdgeException {
        return null;
    }

    @Override
    public V replace(Vertex<V> v, V newElement) throws InvalidVertexException {
        return null;
    }

    @Override
    public E replace(Edge<E, V> e, E newElement) throws InvalidEdgeException {
        return null;
    }

    private class MyVertex implements Vertex<V> {
        private V element;

        public MyVertex(V element) {
            this.element = element;
        }

        @Override
        public V element() {
            return element;
        }

        @Override
        public String toString() {
            return String.format("Vertex{%s}", element);
        }
    }

    private class MyEdge implements Edge<E, V> {
        private E element;

        public MyEdge(E element) {
            this.element = element;
        }

        @Override
        public E element() {
            return element;
        }

        @Override
        public Vertex<V>[] vertices() {
            //if the edge exists, then two existing vertices have the edge
            //in their incidentEdges lists
            for(Vertex<V> v : GraphAdjacencyMatrix.this.adjacencyMap.keySet()) {

                for (Map.Entry<Vertex<V>, Edge<E, V>> entry : adjacencyMap.get(v).entrySet()) {
                    if( entry.getValue().equals( this )) {
                        return new Vertex[]{v, entry.getKey()};
                    }
                }
            }

            return new Vertex[]{null, null}; //edge was removed from the graph
        }

        @Override
        public String toString() {
            return String.format("Edge{%s}", element);
        }
    }

    private MyVertex checkVertex(Vertex<V> v) throws InvalidVertexException {
        if(v == null) throw new InvalidVertexException("Null vertex.");

        MyVertex vertex;
        try {
            vertex = (MyVertex) v;
        } catch (ClassCastException e) {
            throw new InvalidVertexException("Not a vertex.");
        }

        if (!adjacencyMap.containsKey(v)) {
            throw new InvalidVertexException("Vertex does not belong to this graph.");
        }

        return vertex;
    }

    private MyEdge checkEdge(Edge<E, V> e) throws InvalidEdgeException {
        if(e == null) throw new InvalidEdgeException("Null edge.");

        MyEdge edge;
        try {
            edge = (MyEdge) e;
        } catch (ClassCastException ex) {
            throw new InvalidVertexException("Not an edge.");
        }

        if (!edges().contains(edge)) {
            throw new InvalidEdgeException("Edge does not belong to this graph.");
        }

        return edge;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Graph | Adjacency Matrix : \n");

        for(Vertex<V> v : adjacencyMap.keySet()) {

            sb.append( String.format("%10s | ", v) );

            for (Map.Entry<Vertex<V>, Edge<E, V>> entry : adjacencyMap.get(v).entrySet()) {
                sb.append( String.format("%10s ---> %-10s + ", entry.getValue(), entry.getKey()) );
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
