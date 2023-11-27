import { useEffect, useState, useCallback } from "react"

export function Users({ token }) {
    const [users, setUsers] = useState(null)

    const loadUsers = useCallback(() => {
        fetch(new URL("/users", new URL(import.meta.env.VITE_BACKEND_URL)), {
            headers: {
                "Authentication": token,
            }    
        })
        .then(response => response.json())
        .then(users => setUsers(users))
    }, [token])

    useEffect(() => {
        loadUsers()
    }, [loadUsers]);

    return <>
        {users == null || users.length == undefined ? <p>No users</p> : <>
            <button type="button" onClick={loadUsers}>Reload</button>
            <table>
                <thead>
                    <tr>Username</tr>
                </thead>
                <tbody>
                    {users.map((user, index) => <tr key={index}><td>{user.username}</td></tr>)}
                </tbody>
            </table>
        </>
        }
    </>
}