1 c++基础知识
1.1 class是对struct的扩展
	含有数据成员，成员函数
1.2 访问控制
	private, protected, public
	private只供类内部的函数使用
	public类外的程序可使用
1.3 this指针
	在类的成员函数中使用，指向当前对象
1.4 类和对象
	int a; // int是类型, a是变量
	Person per; // Person是类, per是对象
1.5 程序结构
1.5.1 类定义(.h)/类实现(.cpp)
1.5.2 命名空间
	.h/.cpp文件中: 
		namespace a
		//声明或定义函数; 
		int fun();
		void fun2()...
	调用者源文件中: 
		1. 直接使用: 
			a::fun, a::fun2
		2. using声明:
			using a::fun;  // 以后调用fun即表示a::fun
		3. using编译:
			using namespace a; // 以后调用fun, fun2即可
1.6 重载
	函数名相同，参数不同(类型、数量、顺序不同)
1.7 指针和引用
	引用就是别名，引用定义时必须初始化: int a; int &b=a; //b即为a的别名
1.8 构造函数/析构函数
1.8.1 构造函数:
	无返回值，函数名与类名相同，可设置默认参数: fun(int a=1, int b=2)
1.8.2 有默认的无参构造函数，一旦实现了自己的构造函数就必须自己实现无参构造函数
1.8.3 无参对象定义: Person p; 不能写为 Person p(); //后者是声明一个函数
1.8.4 类中含有对象成员时，先调用对象成员的默认构造函数，再调用类的构造函数
	要调用对象成员的其他构造函数，可以这样写: Student(int sID) : Person(sID) 
	构造函数后加":"，加上成员的初始化代码
	对象成员：按定义时的顺序构造，与上述初始化代码顺序无关
	析构函数被调用的顺序：相反
1.8.5 构造顺序
	按运行中定义对象的顺序调用构造函数，静态对象只调用一次构造函数; 全局对象在main函数执行前被构造
1.8.6 拷贝构造函数:Person(const Person & s)
	用于：Person s("lisi"); Person p(s);
	若不提供此函数，则调用默认拷贝构造函数，对各成员变量进行值复制；
	对于指针来说，指向同一内存，不能重新分配内存；
	因此需要自己提供拷贝构造函数，实现内存的分配和拷贝
1.8.7 析造函数
	无返回值，无参数，函数形式为"~类名()"
	对象销毁前瞬间，由系统自动调用析构函数
1.9 new和delete
	Person *p = new Person(); delete p;
	Person *pers = new Person[10]; delete[] pers;
1.10 静态成员
1.10.1 静态数据成员
	类中定义，类外分配空间和初始化，初始化时不加static
	class Person{ staitc int cnt;}
	int Person::cnt = 0; // 放在main函数之外
1.10.2 静态成员函数
	在类外定义不加static，不能访问非static成员
	调用：Person::getCount();
1.11 友元
	在类中声明非成员函数时加上friend，它即可访问类的私有数据成员
	一个类的成员函数也可以是另一个类的友元
	class A {
	private :
		int a;
	public:
		friend void fun1();   // fun1不是A的成员函数也可以访问a
		frined void B::fun2(); // B类的fun2也可以访问a
	};
1.12 运算符重载
1.12.1 作为类外函数重载
	Point operator+(cont Point& p1, cont Point& p2) /* p1,p2相加 */
1.12.2 作为类的成员函数重载
	Point operator+(cont Point& p1)  /* this 当前对象和p1相加 */
1.12.3 增量操作符(++, --)
	1 前增量操作
		类外函数：Point& operator++(cont Point& p1) /* p1增加后, return p1 */
		成员函数：Point& operator++(void) /* this对象增加后, return *this */
	2 后增量操作
		类外函数：Point operator++(cont Point& p1, int b) Point n = p1; p1增加; return n;
		成员函数：Point operator++(intb) Point n = *this;  *this增加; return n;
	3 返回结果
		值返回：返回函数内部定义的局部变量；该变量在函数执行时被创建，执行完毕时被销毁；只返回值；效率低
		引用返回：效率高
		选择原则：不影响运算结果，效率优先
1.13 const成员函数
	成员函数声明后面，加上const
		void printInfo(void) const;
	表示此函数没有修改操作
	const对象只能调用const成员函数

2 面向对象编程
2.1 封装
2.1.1 抽象出数据成员、成员函数
2.1.2 访问控制
	1 类本身
		private，外界不可见，不能直接访问
		protected，外界不可见，不能直接访问；子类可以访问
		public，外界可以直接访问
	2 继承访问控制
		无论哪种继承方式，在派生类内部使用父类时并无差别
		不同的继承方式，会影响这两方面：外部代码对派生类的使用、派生类的子类
2.2 继承
2.2.1 形式
	class Father{
	private:
		int money;
	public:
		int getMoney(void);
		void it_skill(void);
	}
	class Son : public Father{
	private:
		int toy;
	}
	Son s;
	s.it_skill();
	s.getMoney();
	1 继承访问控制
		派生类不能直接访问基类的私有成员，可通过protected/public的成员函数访问
		派生类可以访问父类的protected成员，其他代码不可以
		Son可见到的成员，Son可以修改它的权限。private: using Father::getMoney;
	2 派生类对象的数据空间
		money   基类部分 (Father)
		toy     派生部分 (Son)
	3 类型转换
		Student s; Person &p = s;
2.2.2 多继承
	1 一个派生类可以有多个基类
		class Sofabed : public Sofa, public Bed;
	2 问题：二义性
		class Sofa里有weight, class Bed里也有weight
		那么Sofabed就有2个weight，
		既别扭，也有二义性──用哪个weight ?
	3 解决方法(虚拟继承)
		虚基类使得：多个类派生出的对象只继承一个基类对象
		即：Sofabed的基还Sofa, Bed共享一个Furniture对象
		1．从Sofa, Bed中取出公共特性，创建新类Furniture: 它含有weight
		2．Sofa, Bed虚拟继承Furniture
		3．Sofabed多重继承Sofa, Bed
2.2.3 构造
	1 构造顺序
		1. 虚拟基类构造函数：按继承顺序,只执行一次
		2. 非虚拟基类构造函数：按继承顺序
		3. 类的对象成员(按声明的顺序)
		4. 类自己的构造函数
		class Son:public Father, public Mother {
		private:
			int toy;
			Teacher t;
			Room r;
		public:
			// 先调用基类的构造函数: 先Father后Mother，按继承顺序
			// 再调用对象成员的构造函数：先t后r, 按声明顺序
			// 最后调用Son的构造函数
			// 先调用Father,Mother,t,r的无参构造函数
			Son(int money, int toy)   

			// 先调用Father的有参构造函数，Mother的无参构造函数
			// 再调用t的有参构造函数, r的无参构造函数
			// 最后调用Son的构造函数
			Son(int money, int toy, char *music) : Father(money), t1(music)   
		};
	2 析构顺序
		与构造顺序相反
2.2.4 多态
	1 多态的引入
		派生类覆写了基类的函数func;
		Base b;
		Sub  s;
		void test_func(Base &b)  b.func(); 
		test_func(b); // 调用Base的func 
		test_func(s); // 想调用Sub的func怎么办？
		class Human {
		public:
			void eating(void)  cout<<"use hand to eat."<<endl;
		};
		class Englishman : public Human {
		public:
			void eating(void)  cout<<"use knife to eat."<<endl;
		};
		class Chinese : public Human {
		public:
			void eating(void)  cout<<"use chopsticks to eat."<<endl;
		};
		void test_func(Human& h){
			h.eating();
		}
		Englishman e;
		Chinese c;
		test_func(e);
		test_func(c);
		func是一般的函数时，无法达到上述目的，需要把func设置为虚函数，加上virtual前缀
		原理：	对于虚函数，采用动态联编：有虚函数的对象里有一个指针，指向虚函数表；
			调用虚函数时，会根据对象里的指针找到表，从表中取出函数来执行
			对于非虚函数，采用静态联编：编译时就确定调用哪个函数
		差别：	静态联编效率高，动态联编支持多态
	2 多态的限制
		test_func(Human* h):
		test_func(Human& h)：使用指针或引用来使用对象时，才有多态
		test_func(Human h)：传值时，无多态
		只有类的成员函数才能声明为虚函数
		静态成员函数不能是虚函数
		内联函数不能是虚函数
		构造函数不能是虚函数
		析构函数一般都声明为虚函数
		重载：函数参数不同，不可设为虚函数
		覆盖：函数参数、返回值相同，可以设为虚函数
		返回值例外：
		函数参数相同，但是返回值是当前对象的指针或引用时，也可以设为虚函数
		class Human {
		public:
			virtual Human* test(void)  cout<<"Human's test"<<endl; return this; 
		};
		class Englishman : public Human {
		public:
			virtual Englishman* test(void)  cout<<"Englishman's test"<<endl; return this; 
		};
		class Chinese : public Human {
		public:
			virtual Chinese* test(void)  cout<<"Chinese's test"<<endl; return this; 
		};
		void test_return(Human& h){
			h.test();
		}
		Englishman e;
		Chinese c;
		test_return(e);
		test_return(c);
	3 类型转换
		隐式类型转换
			double d = 100.1;
			int i = d;  // double转为int
			char *str = "100ask.taobao.com";
			int *p = str; // char *转为int * 
		显式类型转换
		1 dynamic_cast
			格式：dynamic_cast < type-id > ( expression )
			该运算符把expression转换成type-id类型的对象。
			Type-id必须是类的指针、类的引用或者void *；
			如果type-id是类指针类型，那么expression也必须是一个指针；
			如果type-id是一个引用，那么expression也必须是一个引用。
			1. 用于多态场合，即：必须有虚函数
			2. 主要用于类层次间的上行转换和下行转换，还可以用于类之间的交叉转换
			3. 在类层次间进行上行转换时，dynamic_cast和static_cast的效果是一样的；
			   在进行下行转换时，dynamic_cast具有类型检查的功能，比static_cast更安全。
		2 static_cast
			格式：static_cast < type-id > ( expression )
			该运算符把expression转换为type-id类型，但没有运行时类型检查来保证转换的安全性
			1. 用于类层次结构中基类和子类之间指针或引用的转换。
			2. 进行上行转换（把子类的指针或引用转换成基类表示）是安全的；
			3. 进行下行转换（把基类指针或引用转换成子类指针或引用）时，由于没有动态类型检查，所以是不安全的。
			4. 用于基本数据类型之间的转换，如把int转换成char，把int转换成enum：这种转换的安全性也要开发人员来保证。
			5. 把void指针转换成目标类型的指针(不安全!!)
			6. 把任何类型的表达式转换成void类型。
			注意：static_cast不能转换掉expression的const、volitale、或者__unaligned属性。
		3 reinterpret_cast
			格式：reinterpret_cast<type-id> (expression)
			相当于C风格的用小括号"(type-id)"实现的强制类型转换
			1. type-id必须是一个指针、引用、算术类型、函数指针或者成员指针
			2. 它可以把一个指针转换成一个整数，也可以把一个整数转换成一个指针
			3. 跟C风格的强制转换类似，没有安全性检查
		4 const_cast
			格式：const_cast<type_id> (expression)
			该运算符用来去除原来类型的const或volatile属性。
			除了const 或volatile修饰之外， type_id和expression的类型是一样的。

3 高级编程
3.1 抽象
3.1.1 纯虚函数
	virtual函数声明时后面加上 "=0"
		virtual void eating(void) = 0;
	纯虚函数不需要定义
3.1.2 抽象类：含有纯虚函数的类
	抽象类不能有实例对象
	若子类没有覆写所有的纯虚函数，则子类还是抽象类
3.1.3 抽象类界面
	程序分为：应用编程、类编程
	抽象类给应用编程提供固定的接口
	具体类由抽象类派生出来
	具体类编为动态库
	优点：应用编程、类编程互不影响
3.2 模板
3.2.1 函数模板
	1 定义
		template<类型参数表> 
		返回值 函数名(数据参数表)
			函数模板定义体;
		template<typename T>
		T& mymax(T& a, T& b)
			return (a<b)?b:a;
	2 使用
		1. 函数模板只是编译指令，一般写在头文件中；
		2. 编译程序时，编译器根据函数的参数来“推导”模板的参数；然后生成具体的模板函数
		示例代码：int a; int b; mymax(a, b);
		编译器根据函数参数a, b推导出模板参数为int，所以把模板中的T绑定为int；
		编译程序时生成如下函数：
		int& mymax(int& a, int& b)
			return (a<b)?b:a;
	3 函数推导过程
		1 有限的类型转换
			函数模板只支持2种隐式类型转换
				const转换：
					函数参数为非const引用/指针, 它可以隐式转换为const引用/指针
				数组或函数指针转换：
					数组可以隐式转换为“指向第1个元素的指针”
					参数为“函数的名字” 时，它隐式转换为“函数指针”
			其他隐式转换都不支持，比如：算术转换、派生类对象向上转换
		2 苛刻的类型匹配
			参数类型必须完全匹配，如果不能直接匹配，则可以进行“有限的类型转换”，如果还不匹配，推导失败
		3 参数类型为传值时
			忽略实参的const,volatile等属性，因为传值时，会临时生成一个变量，此变量可读可写
		4 可以在函数模板中打印匹配结果
			std::cout<<__PRETTY_FUNCTION__;
		5 推导示例
			int ia = 1, ib = 2;
			double da= 1.2; db= 2.1;
			mymax(ia, ib);   // "函数模板"会根据参数类型生成"模板函数"mymax<int>
			mymax(da, db); // "函数模板"会根据参数类型生成"模板函数"mymax<double>
			mymax(ia, db);  // 错，没有mymax(int&, double&)
			mymax<double>(ia, db); // 错，没有mymax(int&, double&)
			mymax(static_cast<double>(ia), db); // 错，double& a=static_cast<double>(ia); 本身就是错误代码 (想深入理解，请自行学习“右值”)
			double dc=static_cast<double>(ia); mymax(dc, db); //ok
			mymax(ia, 7); // 错, int& b = 7; 本身就是错误代码
			const int cb = 5; mymax(ia, cb); // 错, 没有mymax(int&, const int&)
	4 函数模板重载
		char* mymax(char* a, char* b)
			return strcmp(a, b)<0?b:a;
		template<typename T>
		T* mymax(T* a, T* b)
			return (*a < *b)?b:a;
		template<typename T>
		T& mymax(T& a, T& b)
			return (a<b)?b:a;
		函数选择规则(更精确)
			先列出候选函数，包括普通函数、参数推导成功的模板函数
			这些候选函数，根据“类型转换”来排序(注意：模板函数只支持有限的类型转换)
			如果某个候选函数的参数，跟调用时传入的参数更匹配，则选择此候选函数
			如果这些候选函数的参数匹配度相同：
				如果只有一个非模板函数，则选择它
				如果只有模板函数，则选择“更特化”的模板函数
				否则，最后导致“二义性”错误(ambiguous)
3.2.2 类模板
	1 格式
		1. 声明
			template<typeclass T>
			class AAA 
			/* 使用T表示某种类型，比如: */
			private:
				T obj;
			public:
				void test_func(T& t);
				.....
			;
		2. 定义
			template<typeclass T>
			void AAA<T>::test_func(T& t)  .... 
	2 使用
		用到时再实例化：
			AAA<int> a;
			AAA<double> b;
		事先实例化再使用：
			template AAA<int>;
			AAA<int> a;
	3 定做(类似重载)
		1. 声明
			template<>
			class AAA<int> 
			......
			public:
				void test(void);
			;
		2. 定义
			void AAA<int>::test(void) ...
3.2.3 程序组织方式
	函数模板、类模板，只是一些编译指令，一般来说，它们放在头文件里供其他代码引用
3.3 异常
3.3.1 一句话概括：捕获异常
	1 谁捕获异常？捕捉谁？A捕捉B
		A()
			try
				B();
			C();
	2 谁制造了异常？函数B
		B()
			throw 某个对象:
	3 捕获后怎么处理？
		A()
			try
				B();
			catch(类形 对象)
				//处理
3.3.2 函数B可能抛出多种异常
	函数A中可有多个catch分支
	catch分支中，对于异常对象，先捕获派生类对象，再捕获基类对象，按此顺序排放代码
	未能捕获的异常，将继续抛给上一层的调用者
3.3.3 想知道函数B会抛出什么异常
	1 函数B可以声明异常
		void B(void) thow (int, double, MyException) ... //会抛出int,double,MyException类异常
		void B(void) thow () ... //不会会抛出任何异常
		void B(void)  ... //可能抛出任何异常
	2 如果抛出了意外的异常，可以事先设置处理函数
		#include <iostream>  
		#include <exception>  

		using namespace std;

		void my_unexpected_func ()  cout<<"catch unexpected exception"<<endl;   

		void B(void) throw (int,double)   
			throw 'a'; /* 本应该throw int或double, 但是现在throw char, 这是个意外的异常 */
		
		int main (void)   
			set_unexpected (my_unexpected_func);  
			try   
				B();  
			catch (int)   
				cout<<"catch exception int"<<endl;
			catch (...)   
				/* 拥捉其他被声明的异常 */
				cout<<"catch other expected exception"<<endl;
			return 0;  
	3 对于意料之外的异常,会执行2个函数：
		"unexpected"函数(可以自己提供)，"terminate"函数(可以自己提供)
			#include <exception>  
			void my_terminate_func ()  cout<<"my_terminate_func"<<endl;   
			set_terminate(my_terminate_func);
		"unexpected"函数用来处理声明之外的异常
		"terminate"函数用来处理"catch分支未捉到异常"
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
