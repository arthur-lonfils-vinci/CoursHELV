import { useEffect, useState } from 'react';
import { fetchBooks } from '../../utils/library';
import { Book } from '../../types/books';
import { getAuthenticatedUser } from '../../utils/session';
import { AuthenticatedUser } from '../../types/users';
import { useNavigate } from 'react-router-dom';


export const LibraryPage: React.FC = () => {
    const navigation = useNavigate();

    const [books, setBooks] = useState<Book[]>([]);
    const [isAuthenticatedUser, setIsAuthenticatedUser] = useState(false);

    const handleFetchBooks = async () => {
        try {
            const books = await fetchBooks(getAuthenticatedUser() as AuthenticatedUser);
            setBooks(books);
        } catch (error) {
            console.error(error);
        }
    };

    const handleBookClick = (book: Book) => {
        navigation(`/library/${book.id}`);
    };


    useEffect(() => {
        if(getAuthenticatedUser() === undefined) {
            console.log('User not authenticated');
            return;
        } else {
            setIsAuthenticatedUser(true);
            handleFetchBooks();
        }
    }, []);

    if (!isAuthenticatedUser) {
        return (
            <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
                <h2>You need to login to access the library</h2>
            </div>
        );
    }

    return (
        <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
            <div>
                <h2>Library</h2>
                <ul>
                    {books.map((book) => (
                        <li key={book.id}>{book.title}
                        <button onClick={() => handleBookClick(book)}>Voir Plus</button>
                        </li>
                        
                    ))}
                </ul>
            </div>
        </div>
    );
};