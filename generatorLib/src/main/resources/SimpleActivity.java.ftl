package ${packageImport};

import android.os.Bundle;
import ${basePackage}.base.activity.BaseActivity;
import ${basePackage}.databinding.${bindingName};

/**
 * @author liujiang
 * created at: ${.now}
 * Desc: ${desc}
 */
public class ${activityName} extends BaseActivity<${bindingName}> {

    @Override
    protected ${bindingName} getViewBinding() {
        return ${bindingName}.inflate(getLayoutInflater());
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

}
