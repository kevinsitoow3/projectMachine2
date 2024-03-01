package projectMachine;

//bibliotecas que se utilizan
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Products {
    // ATRIBUTOS
    int code, cost, availableQuantity;
    String nameProduct;

    // Lista productos
    static List<Products> productList = new ArrayList<>();

    // COMPORTAMIENTOS/ACCIONES

    // Productos predefinidos
    static void initializeProducts() {
        // crea productos
        Products product1 = new Products();
        product1.code = 1;
        product1.nameProduct = "Doritos";
        product1.cost = 2700;
        product1.availableQuantity = 19;

        Products product2 = new Products();
        product2.code = 2;
        product2.nameProduct = "Amper-Blessed";
        product2.cost = 3500;
        product2.availableQuantity = 146;

        Products product3 = new Products();
        product3.code = 3;
        product3.nameProduct = "BonBonBum";
        product3.cost = 350;
        product3.availableQuantity = 459;

        Products product4 = new Products();
        product4.code = 4;
        product4.nameProduct = "Pineapple-Monster";
        product4.cost = 6500;
        product4.availableQuantity = 37;

        Products product5 = new Products();
        product5.code = 5;
        product5.nameProduct = "Chocorramo";
        product5.cost = 1800;
        product5.availableQuantity = 11;

        // añade productos a la lista
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
    }

    //Funciones CRUD

    //Metodo para crear un producto
    public void createProduct() {
        //para aceptar la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write product code: ");
        code = scanner.nextInt();

        System.out.println("Write product name: ");
        nameProduct = scanner.next();

        System.out.println("Write product cost: ");
        cost = scanner.nextInt();

        System.out.println("Write available quantity of product: ");
        availableQuantity = scanner.nextInt();

        // para crear un nuevo producto se instancia un objeto de Product
        Products newProduct = new Products();

        newProduct.code = code;
        newProduct.nameProduct = nameProduct;
        newProduct.cost = cost;
        newProduct.availableQuantity = availableQuantity;

        productList.add(newProduct);
        System.out.println("Product created.");

    }
    
    
    //encontrar un producto con el codigo
    public static Products getProductByCode(int code) {
        for (Products product : productList) {
            if (product.code == code) {
                System.out.println("Product found:");
                System.out.println("Code: " + product.code);
                System.out.println("Name: " + product.nameProduct);
                System.out.println("Cost: " + product.cost);
                System.out.println("Available Quantity: " + product.availableQuantity);
                return product;
            }
        }
        System.out.println("Product with code " + code + " not found.");
        return null;
    }

    // Eliminar producto
    public void deleteProduct(int code) {
        Products productDelete = getProductByCode(code);
        if (productDelete != null) {
            productList.remove(productDelete);
            System.out.println("Product deleted.");
        }
    }

    // Actualizar producto
    public void updateProduct(int code, String newName, int newCost, int newAvailableQuantity) {
        Products productUpdate = getProductByCode(code);//imprime el producto que habia
        if (productUpdate != null) {
            productUpdate.nameProduct = newName;
            productUpdate.cost = newCost;
            productUpdate.availableQuantity = newAvailableQuantity;
            System.out.println("The product has been updated.");
            System.out.println("The new dates are: ");
            getProductByCode(code);//imprime otra vez el mismo producto pero actualizado
        }
    }

    // Ver todos los productos
    public static void printProductList() {
        System.out.println("\nProduct List:");
        for (Products product : productList) {
            System.out.println("Code: " + product.code);
            System.out.println("Name: " + product.nameProduct);
            System.out.println("Cost: " + product.cost);
            System.out.println("Available Quantity: " + product.availableQuantity);
            System.out.println("------------------------");
        }
    }

    public void dispenseProduct(int quantity) {
        if (this.availableQuantity >= quantity) {
            this.availableQuantity -= quantity;
            System.out.println("\nDispensing " + quantity + " " + this.nameProduct + "(s).");
        } else {
            System.out.println("\nInsufficient inventory. Cannot dispense " + quantity + " " + this.nameProduct + "(s).");
        }
    }
    
}
