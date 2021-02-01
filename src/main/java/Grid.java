import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

public class Grid {
    private Pair<Integer,Integer> start;
    private final HashSet<Pair<Integer,Integer>>  finish= new HashSet<>();

    private final String UP="U";
    private final String DOWN="D";
    private final String RIGHT="R";
    private final String LEFT="L";

    private final List<Pair<Integer,Integer>> allLinks = new ArrayList<>();

    public void setStart(Pair<Integer, Integer> start) {
        this.start = start;
    }

    public void setFinish(Pair<Integer, Integer> finishNode) {
        this.finish.add(finishNode);
    }

    public Pair<Integer,Integer> addLink(int x,int y){
        var link = Pair.of(x,y);
        if(!allLinks.contains(link)){
            this.allLinks.add(link);
        }

        return link;
    }

    public void addLink(Pair<Integer, Integer> current) {
        if(!allLinks.contains(current)){
            this.allLinks.add(current);
        }

    }

    public void print(HashSet<Node> visited) {
        System.out.println("Start:" + start);
        System.out.println("Finish:" + finish);

        var w = allLinks.stream()
                .mapToInt(Pair::getLeft).max().getAsInt();

        var h = allLinks.stream()
                .mapToInt(Pair::getRight).max().getAsInt();

        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= w; j++) {
                var curr = Pair.of(j,i);
                if(start!=null && start.equals(curr)){
                    System.out.print("S");
                }else if (finish.contains(curr)){
                    System.out.print("F");
                }else if(visited!=null && visited.stream().anyMatch(v -> v.getPosition().equals(curr))){
                    System.out.print("#");
                }else if(allLinks.contains(Pair.of(j,i))){
                    System.out.print("o");
                }else{
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    public Pair<Integer, Integer> move(Pair<Integer, Integer> current, String dir) {
        switch (dir){
            case UP:
                return Pair.of(current.getLeft(),current.getRight()-1);
            case DOWN:
                return Pair.of(current.getLeft(),current.getRight()+1);
            case RIGHT:
                return Pair.of(current.getLeft()+1,current.getRight());
            case LEFT:
                return Pair.of(current.getLeft()-1,current.getRight());
            default:
                return current;
        }
    }

    public List<Node> getAdjacentNodes(Node node){
        List<Node> result= new ArrayList<>();
        var current = node.getPosition();
        result.add(new Node(Pair.of(current.getLeft(),current.getRight()-1),node));
        result.add(new Node(Pair.of(current.getLeft(),current.getRight()+1),node));
        result.add(new Node(Pair.of(current.getLeft()+1,current.getRight()),node));
        result.add(new Node(Pair.of(current.getLeft()-1,current.getRight()),node));
        result.removeIf(n -> !this.allLinks.contains(n.getPosition()));

        return result;
    }

    public void parseInput(java.util.List<String> input) {
        for (var link:
                input) {
            var matcher = Pattern.compile("([0-9]+),([0-9]+) (.*)").matcher(link);
            while (matcher.find()){
                var current = addLink(Integer.parseInt(matcher.group(1)),
                                                        Integer.parseInt(matcher.group(2)));
                for (var dir:
                        matcher.group(3).split(",")) {
                    if(dir.equals("S")){
                        setStart(current);
                    }else if(dir.equals("F")){
                        setFinish(current);
                    }else {
                        current = move(current,dir);
                        addLink(current);
                    }
                }
            }
        }
    }

    public Node findPath() {
        System.out.println("Finding path");
        HashSet<Node> visited = new HashSet<>();
        Stack<Node> nodes = new Stack<>();
        nodes.add(new Node(start,null));
        while (!nodes.isEmpty()) {
            var current = nodes.pop();
            var neighbours = getAdjacentNodes(current);
            neighbours.removeIf(visited::contains);
            for (var n :
                    neighbours) {
                if (finish.contains(n.getPosition())) {
                    System.out.println("Found finish node" + n);
                    print(visited);
                    return n;
                }
                visited.add(n);
            }
            nodes.addAll(neighbours);
        }
        print(visited);
        return null;
    }
}
