package api.payloads;

import java.util.List;

public class Helper {

    public static boolean isProductsSorted(List<Product> products)
    {
        for(int i =0 ; i< products.size()-1;i++)
        {
            if (products.get(i).getPrice()>products.get(i+1).getPrice())
            {
                return false;
            }

        }
        return true;
    }

    public static boolean isProductsSameType(List<Product> products,String type)
    {
        for(Product product:products)
        {
            if (!product.getType().equalsIgnoreCase(type))
            {
                return false;
            }
        }
        return true;
    }

    public static boolean isProductsSameCategory(List<Product> products,String category)
    {
        for(Product product:products)
        {
            if (!product.getCategories().get(0).getName().equalsIgnoreCase(category))
            {
                return false;
            }
        }
        return true;
    }
}
