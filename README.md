# springBoot-GraphQL
#POST -- Get ALL Books
http://localhost:8080/rest/books

Body : 
    {
    	allBooks{
    		isn
    		title
    		authors
    	}
    }

#POST -- Get Book By ISN
http://localhost:8080/rest/books

Body : 
    {
    	book(id:"123"){
    		isn
    	}
    }