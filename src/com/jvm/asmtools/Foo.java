
public class Foo {
 public static void main(String[] args) {
  boolean flag = true;
  /*	iconst_1;将常量1(true)压操作数栈：true表示为常量1
		istore_1;将操作数栈顶，保存到局部变量表slot1槽位：把true保存到局部变量flag里*/
  if (flag) System.out.println("Hello, Java!");//0f 1t
  /*	iload_1;将局部变量表slot1槽位，载入操作数栈顶：把变量flag放到这
		ifeq	L14;如果操作数栈顶等于0跳转到L14，非0则才继续执行：0false 非0(123)true */
  if (flag == true) System.out.println("Hello, JVM!");
  /*14: iload_1;载入局部变量表slot1槽位：载入flag
		iconst_1;将常量1(true)压操作数栈：true表示为常量1
		if_icmpne	L27;iload_1(slot1:flag)和iconst_1(常量1)两个整数不相等则跳到L27，相等才继续执行
	27: return 不等跳到L27就直接返回了
   */
  /*因此只把第一个iconst_1替换为iconst_2时，即只把flag常量1换成2，第一个if通过，第二个不通过*/
 }
}
