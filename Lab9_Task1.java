
class Shape {
	
	public double calculateArea() {
		return 0;
	}
}

class Circle extends Shape {
	
	double radius;
	
	Circle(double r) {
		this.radius = r;
	}
	
	// calculate area using stored radius
	public double calculateArea() {
		double area = 3.14159 * radius * radius;
		return area;
	}
	
	// overloaded - if someone passes a radius directly
	public double calculateArea(double r) {
		double area = 3.14159 * r * r;
		return area;
	}
}

class Square extends Shape {
	
	double side;
	
	Square(double s) {
		this.side = s;
	}
	
	public double calculateArea() {
		return side * side;
	}
	
	// overloaded version
	public double calculateArea(double s) {
		return s * s;
	}
}

class Triangle extends Shape {
	
	double base;
	double height;
	
	Triangle(double b, double h) {
		this.base = b;
		this.height = h;
	}
	
	public double calculateArea() {
		return (base * height) / 2;
	}
	
	// overloaded - pass base and height manually
	public double calculateArea(double b, double h) {
		return (b * h) / 2;
	}
}

public class Lab9_Task1 {
	
	public static void main(String[] args) {
		
		Circle c1 = new Circle(5);
		Square s1 = new Square(4);
		Triangle t1 = new Triangle(6, 3);
		
		// polymorphism - using parent reference
		Shape sh1 = new Circle(7);
		Shape sh2 = new Square(3);
		Shape sh3 = new Triangle(8, 4);
		
		System.out.println("Circle area: " + c1.calculateArea());
		System.out.println("Square area: " + s1.calculateArea());
		System.out.println("Triangle area: " + t1.calculateArea());
		
		System.out.println("\nUsing Shape reference (polymorphism):");
		System.out.println("Circle: " + sh1.calculateArea());
		System.out.println("Square: " + sh2.calculateArea());
		System.out.println("Triangle: " + sh3.calculateArea());
		
		System.out.println("\nMethod overloading:");
		System.out.println("Circle with radius 10: " + c1.calculateArea(10));
		System.out.println("Square with side 6: " + s1.calculateArea(6));
		System.out.println("Triangle with base 5 height 3: " + t1.calculateArea(5, 3));
	}
}