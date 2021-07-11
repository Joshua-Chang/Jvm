
public class Singleton {
  private Singleton() {}
  private static class LazyHolder {
    static final Singleton INSTANCE = new Singleton();
    static {
      System.out.println("LazyHolder.<clinit>");
    }
  }
  public static Object getInstance(boolean flag) {
    if (flag) return new LazyHolder[2];//被动引用：不会触发类的初始化
    return LazyHolder.INSTANCE;//访问类的静态变量触发类的初始化，初始化语句才会执行
  }
  public static void main(String[] args) {
    getInstance(true);
    System.out.println("----");
    getInstance(false);
  }
  //java -verbose:class Singleton 对class打印类加载的先后顺序
}
