package UIAutoTest.Learn;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNGDemo_LearnTest {

	/**
	 * 日期：2019-10-15
	 * 主题内容：TestNG断言
	 * 本文档主要内容将介绍TestNG中的Assertion，也就是写测试用例中的断言部分。
	 * 一个测试用例的水平高低，主要是看断言的水平。断言能体现出测试的思维和测试角度，所以断言是测试中最难写的部分，自动化测试用例最难的也是在断言。
	 * 我们之前都写过接口的自动化脚本了，我就举例一个接口测试的常见流程
	 * 1）  发送接口请求
	 * 2）  断言接口响应状态是不是200 OK
	 * 3）  断言接口的响应时间低于某一个值（看情况，不是必选）
	 * 4）  断言响应数据是否正确，一般的做法是判断某一个值是否相等或者包含关系
	 * 基本上就是这么一个测试流程，其中4）部分的断言最难写。因为接口请求回来，会得到很多参数返回，一般是json数据，有些公司只能够对其中几个json字符串进行断言比较，有些能够通过两个json文件对比，一个是直接从数据库请求的json数据文件，一个是接口请求得到的json文件。
	 * 那下面我们来看看TestNG给我们提供了哪些断言操作。
	 * TestNG中最常用的一个断言类是Assert.java,里面有多个静态方法，这个类我们习惯叫硬断言。对应的还有一个软断言的类，叫SoftAssert.java，这个类是需要创建实例对象，才能调用相关实例方法进行软断言。
	 * 1. Assert类（硬断言）
	 * 1）  Assert.assertEquals(actual, expected,message1)
	 * TestNG中提供了多个33个assertEquals（）方法，主要是匹配不同的数据类型和集合类操作。我们最常用的就是assertEquals(actual, expected)或者assertEquals(actual,expected，“message1”)，
	 * 下面的例子assertEquals(actual,expected，“message1”)中，如果现实结果和期待结果不相等，抛出断言异常并显示message1的内容。自动化测试一般喜欢带上这个message1，这样抛出错误，更能快速读懂错误的原因和错误的具体业务逻辑。
	 * */
    @Test
    public void AssertTest1(){
        Assert.assertEquals(12,12);
        //Assert.assertEquals('A', 'a');
        Assert.assertEquals("ABc", "ABC", "对比内容不相等，请检查！！！"); 
    }
    /**
     * 2）  assertEqualsNoOrder(（actual, expect），这个判断方法时判断两个【对象】是否相同，忽略排序位置。例如下面的两个字符串数组比较。
     * */
    @Test
    public void AssertTest2(){
 
        String[] st1 = {"Anthony", "Tom", "Jhon"};
        String[] st2 = {"Jhon", "Anthony", "Tom"};
        Assert.assertEqualsNoOrder(st1, st2, "两个字符串数组不相同，请检查！！！");
    }
    /**
     * 3）  assertFalse(布尔条件，“message1”),第三种断言是一个断言为假的方法，断言一个条件，如果返回是假，则测试通过，如果返回是真，抛出断言异常，打印message1的内容。
     * */
    @Test
    public void AssertTest3(){
 
        String[] st1 = {"Anthony", "Tom", "Jhon"};
        String[] st2 = {"Jhon", "Anthony", "Tom"};
        Assert.assertFalse(st1==st2, "断言st1数组与st2数据相等不通过，原因两个相等请检查！！！");
    }
    /**
     * 备注：上面两个字符串数组，如果考虑排序位置，肯定两者不相等。
     * */
    /**
     * 4）  assertNotEquals(actual, expect, “message1”),提供“不相等”断言方法，也是有6个针对不同数据类型的不相等的断言方法。这里我们介绍两个对象的不相等举例，如果相等，输出message1的内容。
     * */
    @Test
    public void AssertTest4(){
 
        String[] st1 = {"Anthony", "Tom", "Jhon"};
        String[] st2 = {"Jhon", "Anthony", "Tom"};
        Assert.assertNotEquals(st1, st2, "两者相等");
    }
    /**
     * 5） assertNotNull(object, “message1”),对象非空判断，断言对象不为空，否则，抛出异常，打印message1的内容。下面的例子中st3会抛出异常。
     * */
    @Test
    public void AssertTest5(){
 
        String[] st1 = {"Anthony", "Tom", "Jhon"};
        String[] st2 = {"Jhon", "Anthony", "Tom"};
        String[] st3 = null;
        Assert.assertNotNull(st1,"st1对象为空");
        Assert.assertNotNull(st3,"st3对象为空");
    }
    /**
     * 6）  assertSame(actual, expect, “message1”),断言两个对象相同，这里相同和上面的Equals不同，Equals是值比较，而Same是内存地址比较。
     * */
    @Test
    public void AssertTest6(){
        String[] st1 = {"Anthony", "Tom", "Jhon"};
        String[] st2 = {"Jhon", "Anthony", "Tom"};
        String[] st3 = st1;
        Assert.assertSame(st1, st3, "st1 and st3 not same");
        Assert.assertSame(st1, st2, "st1 and st2 not same");
    }
    /**
     * 7）  assertNotSame(actual, expect, “message1”),同样的，也有断言两个对象内存地址不相同的断言方法。
     * 下面例子中的st1和st2肯定内存地址不相同，所以assertNotSame是运行通过，如果把st2改成st3，这个就会抛出异常并打印same这个字符消息，因为st1和st3指向同一个内存位置。
     * */
    @Test
    public void AssertTest7(){
 
        String[] st1 = {"Anthony", "Tom", "Jhon"};
        String[] st2 = {"Jhon", "Anthony", "Tom"};
        String[] st3 = st1;
        Assert.assertNotSame(st1, st2, "st1 and st2 same");
        Assert.assertNotSame(st1, st3, "st1 and st3 same");
        
    }
    /**
     * 8）  assertTrue(条件表达式, “message1”) 提供了断言一个条件为真的方法，如果条件表达式为真，该断言运行通过，如果表达式为假，抛出异常，打印message1的消息。
     * */
    @Test
    public void AssertTest8(){
 
        String[] st1 = {"Anthony", "Tom", "Jhon"};
        String[] st2 = {"Jhon", "Anthony", "Tom"};
        String[] st3 = st1;
        Assert.assertTrue(st1==st3, "两者不相同");
    }
    /**
     * 9) Assert.fail();自定义断言失败
     * */
    @Test
    public void AssertTest9(){
 
        String str1 = "Jhon";
        String str2 = "Jhon";
        Assert.fail("失败用例测试！！！");
    }
    /**
     * 10) Assert.assertThrows()断言一段可执行程序有异常抛出
     * */
    @Test
    public void AssertTest10(){
        Assert.assertThrows(null);
    }
    /**
     * 2.  SoftAssert（软断言）在Assert.java这个类中，上面我们已经介绍了大部分常用的断言方法。这些断言方法都是叫硬断言。硬断言就是，如果运行到折行断言失败，即使该用例，后面还有其他代码行，也不会继续执行下去。
     * 有时候我们不希望这种情况出现，SoftAssert就很好地帮我们解决了这个问题。SoftAssert的特点如下：
     * 1）  如果一个断言失败，会继续执行这个断言下的其他语句或者断言。
     * 2）  也就是一个用例有多个断言，失败了其中一个，不影响其他断言的运行
     * 3）  不要忘记调用assertAll()在该用例的最后一个断言后面。
     * */
    
    @Test
    public void testSoftAssert(){
        System.out.println("Test start");
        SoftAssert assertion = new SoftAssert();
        assertion.assertEquals(12, 13,"两者不相等");
        System.out.println("Test complete");
        System.out.println(3+8);
        assertion.assertAll();
    }
    /**
     * 我们可以看到在断言12和13相等的这行代码后面还有其他的语句，如果这里采用的是Hard Assert，那么Test complete 和 11是不会输出，也就是代码没有被执行。这个就是SoftAssert的优点，再强调一次，最后一定要调用assertAll()方法。
     * 软断言为什么最后一定要调用assertAll()?
     * 因为这个方法用来抛出 断言的异常信息，不加的话，断言的错误信息就出不来.
     * */
}
