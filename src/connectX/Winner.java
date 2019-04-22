/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectX;

/**
 *
 * @author Unknown
 */
public class Winner {
    int NOR;
    int NOC;
    int connectType;
    int[][] result;
    int [] availablePlaces;
    public Winner() {
        
    }

    public Winner(int NOC, int NOR,int connectType) {
        this.NOC=NOC;
        this.NOR=NOR;
        this.connectType=connectType-1;
        result = new int[NOR][NOC];
        availablePlaces=new int[NOC];
        for(int i=0;i<NOC;i++)
            availablePlaces[i]=NOR;
    }
    public void updateData (int column,int playerN){
        int row = availablePlaces[column]-1;
        result[row][column]= playerN;
        availablePlaces[column]--;
    }
    public boolean isAvailable(int colunm){
        if (availablePlaces[colunm]==0)
            return false;
        return true;
    }
    public void restData(){
        for(int i=0;i<NOC;i++){
            availablePlaces[i]=NOR;
            for(int j=0;j<NOR;j++){
                result[j][i]= 0;
            }
        }
    }

    public boolean chickWinner(int column, int playerN) {
        int row = availablePlaces[column];
        int winRow = 0;
        int d = 0;
        int cross1 = 0;
        int cross2 = 0;
        //check row
        int a = 1;
        while ((column - a) >= 0 && result[row][column - a] == playerN) {
            winRow++;
            a++;
        }
        a = 1;
        while ((column + a) < NOC && result[row][column + a] == playerN) {
            winRow++;
            a++;
        }
        if (winRow >= connectType) {
            System.out.println("win");
            return true;
        }
        //check column
        for (a = 1; a < (connectType+1); a++) {
            if ((row + a) < NOR && result[row + a][column] == playerN) {
                d++;
            }
        }
        if (d >= connectType) {
            System.out.println("win");
            return true;
        }
        //check cross 1 \
        a = 1;
        while ((row - a) >= 0 && (column - a) >= 0 && result[row - a][column - a] == playerN) {
            cross1++;
            a++;
        }
        a = 1;
        while ((row + a) < NOR && (column + a) < NOC && result[row + a][column + a] == playerN) {
            cross1++;
            a++;
        }
        if (cross1 >= connectType) {
            System.out.println("win");
            return true;
        }
        //check cross 2 /
        a = 1;
        while ((row - a) >= 0 && (column + a) < NOC && result[row - a][column + a] == playerN) {
            cross2++;
            a++;
        }
        a = 1;
        while ((row + a) < NOR && (column - a) >= 0 && result[row + a][column - a] == playerN) {
            cross2++;
            a++;
        }
        if (cross2 >= connectType) {
            System.out.println("win");
            return true;
        }
        return false;
    }
}
