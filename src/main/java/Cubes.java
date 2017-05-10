

/**
 * Created by Happy on 5/9/2017.
 */
//Thhis class represents objects stored in the databse
public class Cubes {

        String name;
        double time;
        //constructor
        Cubes (String n, double t){
            name = n;
            time = t;
        }
        @Override
        public String toString(){
            return "Cube Solver's name: "+ name + " Time taken to solve: " + time;
        }
    }



