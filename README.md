# springBoot-GraphQL
#POST -- Get ALL Books
http://localhost:8080/rest/books

Body : 
    {
    	mongoAllBooks{
    		isn
    		title
    		authors
    	}
    }

#POST -- Get Book By ISN
http://localhost:8080/rest/books

Body : 
    {
    	mongoBook(id:"123"){
    		isn
    	}
    }