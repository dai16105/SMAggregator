package gr.uom.smaggregator;

public class FBFriend {
    private String id;
    private String name;

    public FBFriend(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }
}
