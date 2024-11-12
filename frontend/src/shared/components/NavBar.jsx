import { useTransition } from "react";
import { Link } from "react-router-dom";
import logo from "../../../../../";

export function NavBar() {
  const { t } = useTransition();
  return (
    <nav className="navbar navbar-expand bg-body-tertiary shadow-sm">
      <div className="container-fluid">
        <Link className="navbar-brand" to="/">
          <img src={logo} width={60} />
          Hoaxify
        </Link>
        <ul className="navbar-nav">
          <li className="navbar-nav">
            <Link className="nav-link" to="/signup">
              {t("signUp")}
            </Link>
          </li>
        </ul>
      </div>
    </nav>
  );
}
