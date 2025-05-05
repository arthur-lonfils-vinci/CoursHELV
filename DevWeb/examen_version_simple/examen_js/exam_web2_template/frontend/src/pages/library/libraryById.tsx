import { useEffect, useState } from "react";
import { Book } from "../../types/books";
import { fetchBookById } from "../../utils/library";
import { getAuthenticatedUser } from "../../utils/session";
import { AuthenticatedUser } from "../../types/users";
import { useParams } from "react-router-dom";
import coverImg from '../../assets/cover.jpg';

export const LibraryPageById: React.FC = () => {

    const { bookId: bookIdParam } = useParams();
    const [book, setBook] = useState<Book | null>(null);

    useEffect(() => {
        const fetchBook = async () => {
            // fetch book by id
            console.log(bookIdParam);
            const bookId = Number(bookIdParam);
            const bookFetched: Book = await fetchBookById(bookId, getAuthenticatedUser() as AuthenticatedUser);
            if (bookFetched.image == undefined){
                bookFetched.image = coverImg;
            };
            setBook(bookFetched);
            console.log(bookFetched);
        };
        fetchBook();
    }, [bookIdParam]);

    if(getAuthenticatedUser() === undefined) {
        return (
            <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
                <h2>You need to login to access the library</h2>
            </div>
        );
    }

    return (
        <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
            <div>
                <h2>Book</h2>
                {book && (
                    <div>
                        <img src={book.image} alt={book.title} />
                        <h3>{book.title}</h3>
                        <p>{book.author}</p>
                        <p>{book.creationDate}</p>
                    </div>
                )}
            </div>
        </div>
    );
}