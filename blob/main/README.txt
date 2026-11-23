JAVA DATABASE FINAL PROJECT - RUBRIC-BASED STARTER

This ZIP contains the 10 requested rubric files plus supporting model and repository
classes so the package references and derived repository methods are consistent.

IMPORTANT:
1. These files use Spring Boot 3 / jakarta.persistence imports.
2. Copy into your existing project only after backing up your current files.
3. Check your pom.xml. If you use Spring Boot 2, change jakarta.* imports to javax.*.
4. The exact hidden criteria for Question 6 and Question 8 were not included in the
   feedback supplied, so those two implementations are based on the visible summaries.
5. Review your assignment's original project specification for any extra routes,
   method names, response formats, or entity fields.
6. Compile and test the project before submitting. This ZIP is not a guarantee of
   full marks and has not been tested against your original project/database.
7. If your existing entities differ, reconcile fields and relationships before
   replacing them. In particular, OrderDetails uses a Product relationship and
   Inventory uses Product and Store relationships.
8. The Inventory filter route uses the literal word "null" to skip a filter because
   all three path segments are required by the rubric URL pattern.
