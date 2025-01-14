import React, { useEffect, useState } from 'react';
import { loginUser, logoutUser } from '../../utils/connection';
import { MaybeAuthenticatedUser } from '../../types/users';
import { getAuthenticatedUser } from '../../utils/session';

const HomePage: React.FC = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const [authenticatedUser, setAuthenticatedUser] = useState<MaybeAuthenticatedUser>();

    const handleLogin = (e: React.FormEvent) => {
        e.preventDefault();
        loginUser({ username, password }, (user) => setAuthenticatedUser(user));
    };

    const handleLogout = () => {
        setAuthenticatedUser(undefined);
        logoutUser();
    };

    useEffect(() => {
        setAuthenticatedUser(getAuthenticatedUser());
    }, []);

    if (getAuthenticatedUser()) {
        return (
            <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
                <h2>Welcome {authenticatedUser?.username}</h2>
                <button onClick={handleLogout}>Logout</button>
            </div>
        );
    }

    return (
        <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
            <form onSubmit={handleLogin} style={{ display: 'flex', flexDirection: 'column', width: '300px' }}>
                <h2>Login</h2>
                <label>
                    Username:
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </label>
                <label>
                    Password:
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </label>
                <button type="submit">Login</button>
            </form>
        </div>
    );
};

export default HomePage;