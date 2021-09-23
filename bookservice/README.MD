prerequisites installed : Github, JAVA 11,  Gradle, Postman.
1) in terminal rum
   git clone https://github.com/garyboiskin/book-service.git
2) cd book-service/bookservice
3) run
   gradle assemble
4) run
   java -jar build/libs/book-service-0.0.1-SNAPSHOT.jar
5)open browser http://localhost:8081/h2-ui and verify two records in book table are available.
6)Run  get  request in Postman  http://localhost:8081/api/v1/books
   and verify output url 
   [
   {
   "isbn": "isbn1",
   "title": "title 1",
   "author": "author",
   "datePublished": "1999-01-20",
   "rating": 20
   },
   {
   "isbn": "isbn2",
   "title": "title 2",
   "author": "author",
   "datePublished": "2000-05-20",
   "rating": 30
   }
   ]
   
7) Run get request in Postman http://localhost:8081/api/v1/books?isbn=isbn1 
   and verify output
{
"isbn": "isbn1",
"title": "title 1",
"author": "author",
"datePublished": "1999-01-20",
"rating": 20
}
 8) Run Post request with body

   {
   "isbn": "isbn4",
   "title": "Title 3",
   "author": "Author",
   "datePublished": "1980-04-07",
   "rating": 5
   }

Then run http://localhost:8081/api/v1/books
and verify results with added new book for  "isbn": "isbn4"
[
{
"isbn": "isbn1",
"title": "title 1",
"author": "author",
"datePublished": "1999-01-20",
"rating": 20
},
{
"isbn": "isbn2",
"title": "title 2",
"author": "author",
"datePublished": "2000-05-20",
"rating": 30
},
{
"isbn": "isbn4",
"title": "Title 3",
"author": "Author",
"datePublished": "1980-04-07",
"rating": 5
}
]

