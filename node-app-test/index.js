import moduleA from "./moduleA.js";
import moduleB, { moduleB_var1, moduleB_func1 } from "./moduleB.js";

console.log("app running...");
console.log(moduleA.moduleA_var1);
moduleA.moduleA_func1();

console.log(moduleB_var1);
moduleB_func1();

moduleB();