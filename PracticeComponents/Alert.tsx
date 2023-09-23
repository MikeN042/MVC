import { ReactNode } from "react";

interface Props {
  children: ReactNode;
  color?:
    | "primary"
    | "secondary"
    | "danger"
    | "success"
    | "warning"
    | "info"
    | "light"
    | "dark";
  dismissible?: boolean;
  onClose?: () => void;
}

function Alert({
  children,
  color = "primary",
  dismissible = false,
  onClose,
}: Props) {
  return (
    <div
      className={
        "alert alert-" +
        color +
        (dismissible ? " alert-dismissible fade show" : "")
      }
      role="alert"
    >
      {children}
      {dismissible && (
        <button
          type="button"
          className="btn-close"
          data-bs-dismiss="alert"
          aria-label="Close"
          onClick={onClose}
        ></button>
      )}
    </div>
  );
}
export default Alert;
