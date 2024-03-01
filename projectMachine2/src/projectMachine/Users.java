package projectMachine;

//bibliotecas que se utilizan
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Users {
	//ATRIBUTOS
	int id, availableCash;
	String nameUser, lastName;
	
	//Lista de arreglos para usuarios
	static List<Users> listUser = new ArrayList<>();
	
	//COMPORTAMIENTOS/ACCIONES
	
	//Funcion para inicializar usuarios predefinidos
    public static void initializeUsers() {
        Users user1 = new Users();
        user1.id = 1;
        user1.nameUser = "Juan";
        user1.lastName = "Lozano";
        user1.availableCash = 1000000;

        Users user2 = new Users();
        user2.id = 2;
        user2.nameUser = "Kevin";
        user2.lastName = "Correa";
        user2.availableCash = 25000;

        // Añade usuarios a la lista
        listUser.add(user1);
        listUser.add(user2);
    }
	
    //Funciona para mostrar los usuarios
    public static void showUserList() {
        if (listUser.isEmpty()) {
            System.out.println("No registered users.");
        } else {
            System.out.println("\nList of users:");
            for (Users user : listUser) {
                System.out.println("ID: " + user.id);
                System.out.println("Name: " + user.nameUser);
                System.out.println("LastName: " + user.lastName);
                System.out.println("available balance: " + user.availableCash);
                System.out.println("------------------------");
            }
        }
    }
    
	//Funciones CRUD
		
		//crear usuario
	public void createUser() {
		Scanner scanner = new Scanner(System.in);
		
		//Pide los atributos del nuevo usuario
		System.out.println("Write user ID: ");
		id = scanner.nextInt();
		
		System.out.println("Write name user: ");
		nameUser = scanner.next();
		
		System.out.println("Write last name user: ");
		lastName = scanner.next();
		
		System.out.println("Write available cash user: ");
		availableCash = scanner.nextInt();
		
		//se asigna los atributos ingresados a newUser
		Users newUser = new Users();
		newUser.id = id;
		newUser.nameUser = nameUser;
		newUser.lastName = lastName;
		newUser.availableCash = availableCash;
		
		
		listUser.add(newUser);
		System.out.println("User created.");	
	}
	
		//ver usuario
	public Users getUserById(int id) {
		for(Users user: listUser) {
			if(user.id == id) {
				 System.out.println("\nUser found:");
		            System.out.println("ID: " + user.id);
		            System.out.println("Name: " + user.nameUser);
		            System.out.println("Last Name: " + user.lastName);
		            System.out.println("Available Cash: " + user.availableCash);
				return user;
			}
		}
		System.out.println("User with id " + id + " not found.");
		return null; 
	}
	
		//eliminar usuario
	public void deleteUser (int id) {
		Users userDelete = getUserById(id);
		if(userDelete != null) {
			listUser.remove(userDelete);
			System.out.println("User delete.");
		}
	}
	
		//actualizar usuario
	public void updateUser(int id, String newNameUser, String newLastName, int newAvailableCash) {
		Users userUpdate = getUserById(id);
		if(userUpdate != null) {
			userUpdate.id = id;
			userUpdate.nameUser = newNameUser;
			userUpdate.lastName = newLastName;
			userUpdate.availableCash = newAvailableCash;
			System.out.println("\nThe user has been updated.");
			System.out.println("\nThe new dates are: ");
			Users userUpdate2 = getUserById(id);
			
			
			
		}
	}
	
	
	
}
