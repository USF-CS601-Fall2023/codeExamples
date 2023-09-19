package nestedClasses;

public class MyOuterDriver {
    public static void main(String[] args) {
        MyOuter outer = new MyOuter(2);
        MyOuter.MyInner inner  = outer.new MyInner();
        inner.func();
    }
}
