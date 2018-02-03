package ecs.com.app.model;

import java.util.ArrayList;

import ecs.com.app.nxmllibproject.R;

/**
 * Created by ECS-27 on 03-02-2018.
 */

public class DishCollections {


    public static ArrayList<DishDetails>  getDishdetails()
    {
        ArrayList<DishDetails> dishdetails =new ArrayList<>();

        DishDetails dish = new DishDetails();
        dish.setName("Kadai Panner");
        dish.setRating(5);
        dish.setImage(R.drawable.fd1);
        dishdetails.add(dish);

        dish = new DishDetails();
        dish.setName("Malai Kofta");
        dish.setRating(4);
        dish.setImage(R.drawable.fd2);
        dishdetails.add(dish);

        dish = new DishDetails();
        dish.setName("Indian Thali");
        dish.setRating(3);
        dish.setImage(R.drawable.fd3);
        dishdetails.add(dish);

        dish = new DishDetails();
        dish.setName("Chola Poori");
        dish.setRating(3);
        dish.setImage(R.drawable.fd4);
        dishdetails.add(dish);

        dish = new DishDetails();
        dish.setName("Panner Tikka");
        dish.setRating(4);
        dish.setImage(R.drawable.fd5);
        dishdetails.add(dish);


        return dishdetails;
    }
}
