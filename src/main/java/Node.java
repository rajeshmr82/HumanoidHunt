import org.apache.commons.lang3.tuple.Pair;

import java.util.Objects;
import java.util.Optional;
import java.util.Stack;

public class Node {
    private final Pair<Integer,Integer> position;
    private final Node previous;

    public Node(Pair<Integer,Integer> position, Node parent) {
        this.position = position;
        this.previous = parent;
    }

    public Pair<Integer,Integer> getPosition() {
        return position;
    }

    public Optional<Node> getPrevious() {
        return Optional.ofNullable(previous);
    }

    public void printPath() {
        var n = Optional.of(this);
        Stack<String> path = new Stack<>();
        while (n.isPresent()){
            var p = n.get().getPrevious();
            if(p.isPresent()){
                var prevPos = p.get().getPosition();
                var currPos = n.get().getPosition();
                if(prevPos.getLeft()<currPos.getLeft()){
                    path.push("R");
                }else if(prevPos.getLeft()>currPos.getLeft()){
                    path.push("L");
                }else if(prevPos.getRight()<currPos.getRight()){
                    path.push("D");
                }else if(prevPos.getRight()>currPos.getRight()){
                    path.push("U");
                }
            }
            n = n.get().getPrevious();
        }
        StringBuilder p = new StringBuilder();
        while (!path.isEmpty()){
            p.append(path.pop()).append(",");
        }
        System.out.println(p);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node fCell = (Node) o;
        return position.equals(fCell.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public String toString() {
        return "{" +
                "position=" + position +
                '}';
    }

}
