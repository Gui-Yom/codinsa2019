package lyon.codinsa.virus.network;

public class AttackCommand {
    public Integer srcId;
    public Integer dstId;
    public Integer qtCode;
    
    public AttackCommand(Integer src, Integer dst, Integer qt) {
        srcId = src;
        dstId = dst;
        qtCode = qt;
    }
}
