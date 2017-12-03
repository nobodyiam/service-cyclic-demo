## Introduction
This program is to demo the potential disaster brought by cyclic references between services.

This program contains 3 applications:

1. Application A
	* It has a web controller: Controller A, which contains 2 services:
		* a1
		* a2
2. Application B
	* It has a web controller: Controller B, which contains 2 services:
		* b1
		* b2
3. Application C
	* It has a web controller: Controller C, which contains 2 services:
		* c1
		* c2

To demostrate the issue with cyclic references, we designed 2 scenarios:

1. Non cyclic scenario, which has the invocation hierarchy as follows:
	* a1 -> c1
	* b2 -> c2

2. Cyclic scenario, which has the invocation hierarchy as follows:
	* a1 -> b1
	* b2 -> a2

To trigger the disaster, 2 burst requests to a1 and b2 will be injected in the 5th second.

## Quick Start

### Non cyclic scenario

1. Make sure `test.enable-cyclic` in `application.properties` is set to `false`
2. Run `service.cyclic.demo.service.a.ApplicationA`
3. Run `service.cyclic.demo.service.b.ApplicationB`
4. Run `service.cyclic.demo.service.c.ApplicationC`
5. Run `cyclic-demo.jmx` with Apache Jmeter
6. You will find the result is satisfying. Only a very small jitter happened in the 5th second

### cyclic scenario

1. Change `test.enable-cyclic` in `application.properties` to `true`
2. Restart `service.cyclic.demo.service.a.ApplicationA`
3. Restart `service.cyclic.demo.service.b.ApplicationB`
4. Run `cyclic-demo.jmx` with Apache Jmeter
5. You will find the invocations start to fail since the 5th second, and will continue to fail even all the burst requests are gone, i.e. Application A and Application B can not recover from such scenarios by themselves due to the cyclic references between them