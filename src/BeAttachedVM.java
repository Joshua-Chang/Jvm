public class BeAttachedVM {
    public static void main(String[] args) {
        System.out.println("BeAttachedVM start");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("BeAttachedVM end");
    }
}
