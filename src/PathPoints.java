// 
public class PathPoints{
    private int Row, Col;
    
    public PathPoints(int Row, int Col){
        setRow(Row);
        setCol(Col);
    }
    
    public void setRow(int Row){
        this.Row = Row;
    }
    
    public void setCol(int Col){
        this.Col = Col;
    }
    
    public int getRow(){
        return Row;
    }
    
    public int getCol(){
        return Col;
    }
    
    public String toString(){
        return "(" + Row + ", " + Col + ")";
    }
}
