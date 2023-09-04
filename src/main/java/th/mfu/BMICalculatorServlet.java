package th.mfu;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//TODO: add webservlet to "/calbmi"
@WebServlet("/calbmi")
public class BMICalculatorServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO: get parameter from request: "weight" and "height"
        String weight = request.getParameter("weight");
        String height = request.getParameter("height");

        //TODO: calculate bmi
        if (weight != null && height != null && !weight.isEmpty() && !height.isEmpty()) {
            try {
                double weightValue = Double.parseDouble(weight);
                double heightValue = Double.parseDouble(height);
                //calculate bmi
                double bmi = weightValue / (heightValue * heightValue);
                //TODO: determine the built from BMI
                String Build = determineBuild(bmi);
                //TODO: add bmi and built to the request's attribute
                request.setAttribute("bmi",Math.round(bmi));
                request.setAttribute("build", Build);
                request.getRequestDispatcher("/bmi_result.jsp").forward(request, response);
            }catch (Exception e){
                response.getWriter().println("Invalid input");
            }
        }else{
            response.getWriter().println("Please provide weight and height parameters.");
        }
    }

    private String determineBuild(double bmi) {
        if( bmi < 18.5){
            return "underweight";
        }else if(bmi < 25){
            return "normal";
        }else if( bmi < 30){
            return "overweight";
        }else if(bmi < 35){
            return "obese";
        }else{
            return "extremely obese";
        }
    }
    
}
