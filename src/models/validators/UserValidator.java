package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.User;
import utils.DBUtil;


public class UserValidator {
    public static List<String> validate(User e, Boolean user_id_duplicate_check_flag, Boolean password_check_flag) {
        List<String> errors = new ArrayList<String>();

        String user_id_error = _validateUser_id(e.getUser_id(), user_id_duplicate_check_flag);
        if(!user_id_error.equals("")) {
            errors.add(user_id_error);
        }

        String password_error = _validatePassword(e.getPassword(), password_check_flag);
        if(!password_error.equals("")) {
            errors.add(password_error);
        }

        return errors;
    }

    // 社員番号
    private static String _validateUser_id(String user_id, Boolean user_id_duplicate_check_flag) {
        // 必須入力チェック
        if(user_id == null || user_id.equals("")) {
            return "ユーザーIDを入力して下さい";
        }

        // すでに登録されているユーザーIDとの重複チェック
        if(user_id_duplicate_check_flag) {
            EntityManager em = DBUtil.createEntityManager();
            long users_count = (long)em.createNamedQuery("checkRegisterdCode", long.class)
                                .setParameter("user_id", user_id)
                                .getSingleResult();
            em.close();
            if(users_count > 0) {
                return "入力されたユーザーIDはすでに存在しています。";
            }

        }

        return "";
    }

    // パスワードの必須入力チェック
    private static String _validatePassword(String password, Boolean password_check_flag) {
        // パスワードを変更する場合のみ実行
        if(password_check_flag && (password == null || password.equals(""))) {
            return "パスワードを入力して下さい。";
        }
        return "";
    }
}
