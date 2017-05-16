# Mathagician
Math Operation Evaluator and Calculator base on Shunting-yard algorithm. <br />

### Defualt
- Number type: 
  - Integer
  - Double
- Operator: 
  - Basic: +, -, *, /
  - Advance: %, ^, !
- Notation: 
  - Custom Parenthesis (Default: `()`)
- Constant: 
  - pi or π
  - e
  - φ
- Function: 
  - Trigonometry: sin, cos, tan, csc, sec, cot
  
### Examples
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
Note: OperationBuilder#add(Operator|Constant|Function) can accept varargs

### Todo List
- [x] More operators...
- [ ] More Functions... 
- [ ] Variables: OperationBuilder#setVariable("x", "1")