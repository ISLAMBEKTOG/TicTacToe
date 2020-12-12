import java.util.*;

public class Brain180107250 implements Brain{
    @Override
    public int[] getNextMove(IBoard board, char playerSymbol) {
        int height = board.getHeight();
        int width = board.getWidth();

        Vector vector = this.getAllAvailableMoves(board);
        int vectorSize = vector.size();


        if(vectorSize > 0){
            if(isOdd(height,width)){
                int centerX = width / 2;
                int centerY = height / 2;
                char place = board.getTileSymbol(centerX,centerY);

                if(place == '.'){
                    return new int[]{centerX,centerY};
                }else{
                    return minimax(board,playerSymbol);
                }
            }else{
                return minimax(board,playerSymbol);
            }
        }else{
            System.out.println("I don't know where to play!");
        }

        throw new RuntimeException("I don't know where to play!");
    }

    /*I use this method for check the board have center or not*/
    public boolean isOdd(int h,int w){
        if(h % 2 != 0 && w % 2 != 0){
            return true;
        }
        return false;
    }

    /*Mini and Max method*/
    public int[] minimax(IBoard board,char playerSymbol){

        List<int[][]> array = evaluate(board);

        /*In that case I found max and min scores*/
        int max = 0;
        int min = 0;
        for (int[][] move:array){
            int current = evaluateLine(board,move,playerSymbol);
            int oppcurrent = evaluateLineforOpp(board,move,playerSymbol);

            if (max < current){
                max = current;
            }

            if (min < oppcurrent){
                min = oppcurrent;
            }
        }

        for (int[][] move:array){
            int current = evaluateLine(board,move,playerSymbol);
            int oppcurrent = evaluateLineforOpp(board,move,playerSymbol);

            /*Here I check end score(bestscore)*/
            int end = 0;
            if(Math.abs(min) >= max){
                end = min;
            }else{
                end = max;
            }

            /*Here I return next place on table*/
            if (end == current) {
                for (int[] m : move) {
                    char place = board.getTileSymbol(m[0], m[1]);
                    if (place == '.') {
                        return new int[]{m[0], m[1]};
                    }
                }
            }else if(end == oppcurrent){
                for (int[] m : move) {
                    char place = board.getTileSymbol(m[0], m[1]);
                    if (place == '.') {
                        return new int[]{m[0], m[1]};
                    }
                }
            }

        }

        throw new RuntimeException("I don't know where to play!");
    }

    /*Checking all possible moves*/
    public List<int[][]> evaluate(IBoard board){

        int area = board.getHeight() * board.getWidth();

        int[][] newArray = new int[area][2];

        int k = 0;

        while (k < newArray.length) {
            for (int i = 0; i < board.getHeight(); i++) {
                for (int j = 0; j < board.getWidth(); j++) {
                    newArray[k][0] = i;
                    newArray[k][1] = j;
                    k++;
                }
            }
        }

        List<int[][]> Allmoves = new ArrayList<>();

        int res = newArray.length / board.getHeight();

        /*For rows*/
        int q = 0;
        int w = res;

        for (int i = 0; i < res; i++) {
            int[][] arr = new int[res][2];

            int e = 0;

            for (int j = q; j < w; j++) {
                arr[e][0] = newArray[j][0];
                arr[e][1] = newArray[j][1];
                e++;
            }

            w += res;
            q += res;

            Allmoves.add(arr);

        }

        /*For columns*/
        int r = 0;
        for (int i = 0; i < res; i++) {

            int[][] arr = new int[res][2];
            int e = 0;

            for (int j = 0; j < res; j++) {
                arr[e][0] = newArray[r][0];
                arr[e][1] = newArray[r][1];

                r += res;
                e++;
            }
            r = 0;
            r += i + 1;

            Allmoves.add(arr);
        }

        /*For diagonal - 1*/
        int[][] arr = new int[res][2];
        int d1 = res + 1;
        int d = 0;
        int e = 0;
        for (int i = 0; i < res; i++) {
            arr[e][0] = newArray[d][0];
            arr[e][1] = newArray[d][1];
            d += d1;
            e++;
        }

        Allmoves.add(arr);

        int[][] arr1 = new int[res][2];
        int d2 = res - 1;
        int D = res - 1;
        int p = 0;
        for (int i = 0; i < res; i++) {
            arr1[p][0] = newArray[D][0];
            arr1[p][1] = newArray[D][1];
            D += d2;
            p++;
        }

        Allmoves.add(arr1);

        return Allmoves;

    }

    /*Best score for first player*/
    public int evaluateLine(IBoard board,int[][] array, char playerSymbol){
        int score = 1;

        for (int j = 0; j < array.length; j++) {

            int[] ver = array[j];

            char place = board.getTileSymbol(ver[0],ver[1]);

            if (place == playerSymbol){
                score *= 10;
            }else if(place == '.'){
                score *= 1;
            }else {
                score *= 0;
            }

        }
        return score;
    }

    /*Best score for opp. player*/
    public int evaluateLineforOpp(IBoard board,int[][] array, char playerSymbol){
        int score = 1;

        for (int j = 0; j < array.length; j++) {

            int[] ver = array[j];

            char place = board.getTileSymbol(ver[0],ver[1]);

            if (place == playerSymbol){
                score *= 0;
            }else if(place == '.'){
                score *= 1;
            }else {
                score *= 10;
            }

        }
        return score;
    }
}
