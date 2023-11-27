import { useEffect, useState } from "react"

export function Users({ token }) {
    const [users, setUsers] = useState(null)

    useEffect(() => {
        fetch(new URL("/users", new URL(import.meta.env.VITE_BACKEND_URL)), {
            headers: {
                "Authentication": token,
            }    
        })
        .then(response => response.json())
        .then(users => setUsers(users))
    }, [token]);

    return <>
        {users == null ? <p>No users</p> :
            <table>
                <thead>
                    <tr>Username</tr>
                </thead>
                <tbody>
                    { users.map((user, index) => <tr key={index}><td>{user.username}</td></tr>) }
                </tbody>
            </table>
        }
    </>
}