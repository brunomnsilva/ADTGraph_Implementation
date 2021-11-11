/* 
 * The MIT License
 *
 * Copyright 2019 brunomnsilva@gmail.com.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package graph;

import java.util.*;

/**
 * ADT Graph implementation that stores a collection of edges (and vertices) and
 * where each edge contains the references for the vertices it connects.
 * <br>
 * Does not allow duplicates of stored elements through <b>equals</b> criteria.
 *
 * @param <V> Type of element stored at a vertex
 * @param <E> Type of element stored at an edge
 *
 * @author brunomnsilva
 */
public class GraphEdgeList<V, E> implements Graph<V, E> {

    private Map<V, Vertex<V>> vertices;
    private Map<E, Edge<E, V>> edges;

    public GraphEdgeList() {
        this.vertices = new HashMap<>();
        this.edges = new HashMap<>();
    }

    @Override
    public boolean areAdjacent(Vertex<V> u, Vertex<V> v) throws InvalidVertexException {
        checkVertex(u);
        checkVertex(v);

        /* Existe alguma aresta que ligue v e w ?*/
        for(Edge<E, V> e : edges.values()) {
            MyEdge edge = (MyEdge)e;
            if(edge.v1 == v && edge.v2 == u || edge.v1 == u && edge.v2 == v) {
                return true;
            }
        }

        return false;
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

    class MyVertex implements Vertex<V> {

        V element;

        public MyVertex(V element) {
            this.element = element;
        }

        @Override
        public V element() {
            return this.element;
        }

        @Override
        public String toString() {
            return "Vertex{" + element + '}';
        }
    }

    class MyEdge implements Edge<E, V> {

        E element;
        Vertex<V> v1;
        Vertex<V> v2;

        public MyEdge(E element, Vertex<V> v, Vertex<V> w) {
            this.element = element;
            this.v1 = v;
            this.v2 = w;
        }

        @Override
        public E element() {
            return this.element;
        }

        @Override
        public Vertex<V>[] vertices() {
            Vertex[] vertices = new Vertex[2];
            vertices[0] = v1;
            vertices[1] = v2;

            return vertices;
        }

        @Override
        public String toString() {
            return String.format("Edge{%s, v1 = %s, v2 = %s}", element, v1, v2);
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

        if (!vertices.containsKey(vertex.element)) {
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

        if (!edges.containsKey(edge.element)) {
            throw new InvalidEdgeException("Edge does not belong to this graph.");
        }

        return edge;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Graph | Edge List : \n");

        sb.append(String.format("Vertices (%d): \n", numVertices()));
        for (Vertex<V> v : vertices.values()) {
            sb.append("\t").append(v.toString()).append("\n");
        }
        sb.append("\n");
        sb.append(String.format("Edges (%d): \n", numEdges()));
        for (Edge<E, V> e : edges.values()) {
            sb.append("\t").append(e.toString()).append("\n");
        }
        return sb.toString();
    }
}
