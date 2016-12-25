package test;

	class Super {
	       public void f() {
	           System.out.println("Super.f()");
	       }
	    }
	 
	    class   Sub   extends   Super {
	       public void f() {
	           System.out.println("Sub.f()");
	       }
	    }
	 
	    class  Foo {
	       public void  g(Super s) {
	           System.out.println("Foo.g(Super s)");
	           s.f();
	       }
	       public void  g(Sub s) {
	           System.out.println("Foo.g(Sub s)");
	           s.f();
	       }
	    }


public class test{
    public static void main(String[] args) {
    	Foo foo =new Foo();
        Super s =new Sub();
        foo.g(s);
        String str=System.getProperty("java.library.path");
        System.out.println(str);
    	
    	
	}
}