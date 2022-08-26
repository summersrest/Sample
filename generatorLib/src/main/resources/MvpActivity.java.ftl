package ${packageImport};

import android.os.Bundle;
import ${presenterImport};
import ${viewImport};
import ${basePackage}.base.activity.BaseMvpActivity;
import ${basePackage}.databinding.${bindingName};


/**
 * @author liujiang
 * created at: ${.now}
 * Desc: ${desc}
 */
public class ${activityName} extends BaseMvpActivity<${bindingName}, ${presenterName}> implements ${viewName}{

    @Override
    protected ${bindingName} getViewBinding() {
        return ${bindingName}.inflate(getLayoutInflater());
    }

	@Override
    public ${presenterName} createPresenter() {
        return new ${presenterName}(this, this);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        
    }


  
}
