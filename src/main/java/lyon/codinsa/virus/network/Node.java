package lyon.codinsa.virus.network;

import java.util.LinkedList;
import java.util.List;

public class Node {

    private Integer id;
    private Integer production;
    private Integer qtCode;
    private Boolean isServer;
    private Integer typeBonus;
    private Integer owner;
    private Boolean visible;
    private List<Link> neighbors = new LinkedList<>();


    public Node(Integer id, Integer production, Integer qtCode, Boolean isServer, Integer typeBonus, Integer owner) {

        this.id = id;
        this.production = production;
        this.qtCode = qtCode;
        this.isServer = isServer;
        this.typeBonus = typeBonus;
        this.owner = owner;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public Integer getProduction() {

        return production;
    }

    public void setProduction(Integer production) {

        this.production = production;
    }

    public Integer getQtCode() {

        return qtCode;
    }

    public void setQtCode(Integer qtCode) {

        this.qtCode = qtCode;
    }

    public Boolean getIsServer() {

        return isServer;
    }

    public void setIsServer(Boolean isServer) {

        this.isServer = isServer;
    }

    public Integer getTypeBonus() {

        return typeBonus;
    }

    public void setTypeBonus(Integer typeBonus) {

        this.typeBonus = typeBonus;
    }

    public Integer getOwner() {

        return owner;
    }

    public void setOwner(Integer owner) {

        this.owner = owner;
    }

    public Boolean getVisible() {

        return visible;
    }

    public void setVisible(Boolean visible) {

        this.visible = visible;
    }

    public List<Link> getNeighbors() {

        return neighbors;
    }

    public void setNeighbors(List<Link> neighbors) {

        this.neighbors = neighbors;
    }

    @Override
    public String toString() {

        return "Node{" +
               "id=" + id +
               ", production=" + production +
               ", qtCode=" + qtCode +
               ", isServer=" + isServer +
               ", typeBonus=" + typeBonus +
               ", owner=" + owner +
               ", visible=" + visible +
               ", neighbors=" + neighbors +
               '}';
    }
}
