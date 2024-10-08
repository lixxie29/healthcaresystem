import { useState } from "react";

const LoginDoctor = () => {
    const [input, setInput] = useState({
        email: "",
        password: "",
    });

    const handleSubmitEvent = (e) => {
        e.preventDefault();
        if(input.email !== "" && input.password !== ""){
            console.log("form submitted successfully", input);
            alert("successful login");
        }
        alert("please provide a valid input");
    };

    const handleInput = (e) => {
        const { name, value } = e.target;
        setInput((prev) => ({
            ...prev,
            [name]: value,
        }));
    };

    return(
        <form onSubmit={handleSubmitEvent}>
            <div className="form_control">
                <label htmlFor="user-email">Email:</label>
                <input
                    type="email"
                    id="user-email"
                    name="email"
                    placeholder="example@yahoo.com"
                    aria-describedby="user-email"
                    aria-invalid="false"
                    onChange={handleInput}
                />
                <div id="user-email" className="sr-only">
                    pls enter valid email
                </div>
            </div>
            <div className="form_control">
                <label htmlFor="password">Password:</label>
                <input
                    type="password"
                    id="password"
                    name="password"
                    aria-describedby="user-password"
                    aria-invalid="false"
                    onChange={handleInput}
                />
                <div id="user-password" className="sr-only">
                    pls enter valid password
                </div>
            </div>
            <button className="btn=submit">Submit</button>
        </form>
    );
};

export default LoginDoctor;