from app.module.login_model import LoginModel
from app.module.user import User
from app.module.admin_model import Admin

class LoginController:
    """
    Login Controller
    """

    @staticmethod
    def create_user_by_login_input(mail, password):
        """
        Create User or Admin object
        :param mail:
        :param password:
        :return: object
        """
        user_data = LoginModel.check_user_in_db_by_mail_model(mail, password)
        if user_data:
            user_type = user_data[2]
            user_mail = user_data[1]
            user_id = user_data[0]
            if user_type == 'user':
                new_user = User(user_id, user_mail)
                return new_user
            elif user_type == 'admin':
                new_admin = Admin(user_id,user_mail)
                return new_admin