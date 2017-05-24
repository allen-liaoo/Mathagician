# Mathagician
Math Operation Evaluator and Calculator base on Shunting-yard algorithm. <br />

### Default
- Number type: 
  - Integer
  - Double
- Operator: 
  - Basic: +, -, *, /
  - Advance: %, ^, !
  - Comparison: =, <, <=, >, >=, !=
- Notation: 
  - Custom Parenthesis (Default: `()`)
- Constant: 
  - pi or π
  - e
  - φ
- Function: 
  - Trigonometry: sin, cos, tan, csc, sec, cot
  
### Operation Examples
- Usage (Calculation)
```java 
OperationBuilder operation = new OperationBuilder("sin(30)/cos(30)")
    .parse();
double result = operation.build().eval();
// result = 0.5773502691896256;
```

- Usage (Reverse Polish Converter)
```java 
OperationBuilder operation = new OperationBuilder("sin(30)/cos(30)")
    .parse();
String reversePolish = operation.build().toString();
// reversePolish = "30.0 sin 30.0 cos /";
```

- Custom operator

Parameters: Key, Name, Arity (UNARY or BINARY),
Precedence (ADDITION, MULTIPLICATION, POWER, or FORCE), isLeftAssociative
```java 
Operator op = new Operator("√", "sqrt", Operator.ARITY_BINARY, Operator.POWER, false){
    @Override
    public Operand operate(Operand... operands) {
        if(operands[0].getNumber() < 0)
            throw new ArithmeticException("Imaginary number.");

        return new Operand(Math.sqrt(operand[0].getNumber())));
    }
});

OperationBuilder operation = new OperationBuilder("√4")
    .addOperator(op).parse();
double result = operation.build().eval();
``` 
- Custom constant

```java 
Constant cs = new Constant(6.022 * Math.pow(10, 23), "mol", "mole", "L");
OperationBuilder operation = new OperationBuilder("12mol")
    .addConstant(cs).parse();
double result = operation.build().eval();
```
- Custom notation

```java 
OperationBuilder operation = new OperationBuilder("[2^3]+1")
    .setParenthesis("[", "]").parse();
double result = operation.build().eval();
```
- Custom function <br />

```java 
Function ft = new Function("round", 1) {
    @Override
    public Operand function(Operand... operands) {
        return new Operand(Math.round(operand[0].getNumber()));
    }
});
OperationBuilder operation = new OperationBuilder("round(3.14)")
    .addFunction(ft).parse();
double result = operation.build().eval();
```
Note: operation.OperationBuilder#add(Operator|Constant|Function) can accept varargs

### Comparison Examples
- Usage (String Constructor)

```java 
ComparisonBuilder operation = new ComparisonBuilder("sin(30)/cos(30) > 1")
    .parse();
boolean result = operation.build().eval();
// result = false;
```
- Usage (Operations Constructor)

```java 
Operation op1 = new OperationBuilder("sin(30)/cos(30)").parse().build();
Operation op2 = new OperationBuilder("1").parse().build();

ComparisonBuilder operation = new ComparisonBuilder(op1, ">", op2); 
// Do not use ComparisonBuilder#parse when there are already defined operations.

boolean result = operation.build().eval();
// result = false;
```

### Todo List
- [x] More operators...
- [x] More Functions... 
- [x] Comparison Evaluator
- [ ] Better Error Handling (Identifying Errors)
- [ ] Variables: operation.OperationBuilder#setVariable("x", "1")